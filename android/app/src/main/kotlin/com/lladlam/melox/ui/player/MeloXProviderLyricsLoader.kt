package com.lladlam.melox.ui.player

import android.content.Context
import android.os.SystemClock
import android.util.Log
import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.download.MeloXDownloadStore
import com.lladlam.melox.core.lyrics.LyricsDocument
import com.lladlam.melox.core.lyrics.AmlldbLyricsClient
import com.lladlam.melox.core.lyrics.BoundLyricSource
import com.lladlam.melox.core.lyrics.LyricBinding
import com.lladlam.melox.core.lyrics.LyricBindingStore
import com.lladlam.melox.core.music.model.MusicAlbumRef
import com.lladlam.melox.core.music.model.MusicArtistRef
import com.lladlam.melox.core.music.model.MusicResourceId
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import com.lladlam.melox.core.music.provider.LyricsCapability
import com.lladlam.melox.core.music.provider.MeloXMusicProviders
import com.lladlam.melox.core.music.provider.SearchCapability
import com.lladlam.melox.core.network.NeteaseSearchClient
import com.lladlam.melox.playback.PlaybackTrackIdentity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import com.lladlam.melox.ui.settings.MeloXSettingsRuntime

/**
 * Single Now Playing lyric data entry point for every visual lyric style.
 *
 * Provider/network work is never performed by the rendering frame loop. The
 * loader owns in-flight work as well as the small LRU cache, so metadata updates
 * cannot cancel and restart the same lyric download/decryption while the renderer
 * is already animating. Apple Music / EVA / TextPV / Skyline all receive the same
 * stable [LyricsDocument] instance for one media identity.
 */
internal object MeloXProviderLyricsLoader {
    private const val MaxCachedDocuments = 24
    private const val AutomaticSelectionCacheVersion = 3
    private val workerScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val lock = Any()
    private val inFlight = mutableMapOf<String, Deferred<LyricsDocument>>()
    private var preloadJob: Job? = null
    private val cache = object : LinkedHashMap<String, LyricsDocument>(MaxCachedDocuments, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, LyricsDocument>?): Boolean =
            size > MaxCachedDocuments
    }

    private fun cached(key: String): LyricsDocument? = synchronized(lock) { cache[key] }

    private fun remember(key: String, document: LyricsDocument): LyricsDocument = synchronized(lock) {
        cache[key] = document
        document
    }

    suspend fun load(
        context: Context,
        state: MeloXPlaybackUiState,
    ): LyricsDocument {
        val appContext = context.applicationContext
        val mediaId = state.mediaId?.takeIf(String::isNotBlank)
            ?: return LyricsDocument(emptyList())
        val resourceId = PlaybackTrackIdentity.decode(mediaId)
            ?: return LyricsDocument(emptyList())
        val auto = MeloXSettingsRuntime.automaticLyricSelectionEnabled
        val binding = if (auto && MeloXSettingsRuntime.lyricStrongBindingEnabled) {
            LyricBindingStore.read(appContext, resourceId)
        } else null
        val cacheKey = buildString {
            append(if (auto) "auto-v$AutomaticSelectionCacheVersion" else "provider")
            append(':').append(resourceId.source.storageValue).append(':').append(resourceId.value)
            binding?.let { append(":bound:").append(it.stableKey()) }
        }
        cached(cacheKey)?.let { return it }

        // Snapshot Compose/player state before handing work to the IO scope. This
        // avoids reading rapidly changing snapshot state from the worker thread.
        val snapshot = LyricTrackSnapshot(
            resourceId = resourceId,
            title = state.title,
            artist = state.artist,
            album = state.album,
            artworkUrl = state.artworkUrl,
            durationMs = state.durationMs,
            automaticSelection = auto,
            binding = binding,
        )
        return loadSnapshot(appContext, cacheKey, snapshot)
    }

    fun preloadQueue(context: Context, state: MeloXPlaybackUiState, count: Int = 2) {
        val appContext = context.applicationContext
        val automaticSelection = MeloXSettingsRuntime.automaticLyricSelectionEnabled
        val snapshots = state.queue
            .drop((state.currentIndex + 1).coerceAtLeast(0))
            .take(count.coerceIn(0, 4))
            .mapNotNull { entry ->
                val resourceId = PlaybackTrackIdentity.decode(entry.mediaId) ?: return@mapNotNull null
                LyricTrackSnapshot(
                    resourceId = resourceId,
                    title = entry.title,
                    artist = entry.artist,
                    album = "",
                    artworkUrl = entry.artworkUrl,
                    durationMs = 0L,
                    automaticSelection = automaticSelection,
                    binding = if (automaticSelection && MeloXSettingsRuntime.lyricStrongBindingEnabled) {
                        LyricBindingStore.read(appContext, resourceId)
                    } else null,
                )
            }
            .distinctBy { "${it.resourceId.source.storageValue}:${it.resourceId.value}" }
        preloadJob?.cancel()
        if (snapshots.isEmpty()) return
        preloadJob = workerScope.launch {
            for (snapshot in snapshots) {
                val cacheKey = snapshot.cacheKey()
                if (cached(cacheKey) == null) {
                    runCatching { loadSnapshot(appContext, cacheKey, snapshot) }
                }
            }
        }
    }

    private suspend fun loadSnapshot(
        appContext: Context,
        cacheKey: String,
        snapshot: LyricTrackSnapshot,
    ): LyricsDocument {
        cached(cacheKey)?.let { return it }
        val deferred = synchronized(lock) {
            cache[cacheKey]?.let { return it }
            inFlight[cacheKey] ?: workerScope.async {
                loadDocument(appContext, snapshot).also { document ->
                    if (document.lines.isNotEmpty()) remember(cacheKey, document)
                }
            }.also { inFlight[cacheKey] = it }
        }
        return try {
            deferred.await()
        } finally {
            if (deferred.isCompleted) synchronized(lock) {
                if (inFlight[cacheKey] === deferred) inFlight.remove(cacheKey)
            }
        }
    }

    private suspend fun loadDocument(
        appContext: Context,
        snapshot: LyricTrackSnapshot,
    ): LyricsDocument {
        if (snapshot.automaticSelection) {
            snapshot.binding?.let { binding ->
                val bound = loadBinding(appContext, binding)
                // Bindings created by the former source-first algorithm may
                // point at line-timed NetEase lyrics. Do not let such a stale
                // binding suppress a newly available QRC/YRC word timeline.
                if (hasWordTiming(bound)) return bound
            }
            return loadAutomatic(appContext, snapshot)
        }
        return loadCurrentProvider(appContext, snapshot)
    }

    private suspend fun loadAutomatic(
        appContext: Context,
        snapshot: LyricTrackSnapshot,
    ): LyricsDocument = coroutineScope {
        val orderedSources = listOf(
            LyricAutoSource.AmlL,
            LyricAutoSource.QQMusic,
            LyricAutoSource.Netease,
            LyricAutoSource.Current,
        )
        val candidates = orderedSources.mapIndexed { priority, source ->
            async {
                val startedAt = SystemClock.elapsedRealtime()
                val timeoutMs = when (source) {
                    LyricAutoSource.QQMusic -> 30_000L
                    LyricAutoSource.AmlL -> 12_000L
                    LyricAutoSource.Netease,
                    LyricAutoSource.Current -> 15_000L
                }
                val resolved = withTimeoutOrNull(timeoutMs) {
                    loadMatchedSource(appContext, snapshot, source)
                } ?: ResolvedLyrics(LyricsDocument(emptyList()), null)
                Log.d(
                    "MeloXLyricsAuto",
                    "source=$source elapsed=${SystemClock.elapsedRealtime() - startedAt}ms " +
                        "lines=${resolved.document.lines.size} " +
                        "wordLines=${resolved.document.lines.count { it.syllables.isNotEmpty() }} " +
                        "timeout=${resolved.document.lines.isEmpty() && SystemClock.elapsedRealtime() - startedAt >= timeoutMs}",
                )
                AutoLyricCandidate(priority, resolved.document, resolved.binding)
            }
        }.awaitAll()
        val selected = selectAutomaticLyricCandidate(candidates)
        Log.d(
            "MeloXLyricsAuto",
            "selectedPriority=${selected?.priority} lines=${selected?.document?.lines?.size ?: 0} " +
                "wordLines=${selected?.document?.lines?.count { it.syllables.isNotEmpty() } ?: 0}",
        )
        if (selected != null) {
            if (MeloXSettingsRuntime.lyricStrongBindingEnabled) {
                selected.binding?.let { LyricBindingStore.write(appContext, snapshot.resourceId, it) }
            }
            selected.document
        } else {
            loadCurrentProvider(appContext, snapshot)
        }
    }

    private suspend fun loadMatchedSource(
        appContext: Context,
        snapshot: LyricTrackSnapshot,
        source: LyricAutoSource,
    ): ResolvedLyrics {
        if (source == LyricAutoSource.AmlL) {
            val id = snapshot.resourceId.takeIf { it.source == MusicSource.Netease }?.value?.toLongOrNull()
                ?: findMatchedNeteaseTrack(appContext, snapshot)?.id?.value?.toLongOrNull()
                ?: return ResolvedLyrics.Empty
            val document = runCatching { AmlldbLyricsClient().lyrics(id) }.getOrDefault(LyricsDocument(emptyList()))
            return ResolvedLyrics(
                document,
                document.takeIf { it.lines.isNotEmpty() }?.let {
                    LyricBinding(BoundLyricSource.AmlL, resourceValue = id.toString(), title = snapshot.title, artist = snapshot.artist, durationMs = snapshot.durationMs)
                },
            )
        }
        if (source == LyricAutoSource.Current) {
            val document = loadCurrentProvider(appContext, snapshot)
            return ResolvedLyrics(document, document.takeIf { it.lines.isNotEmpty() }?.let {
                LyricBinding(BoundLyricSource.Provider, snapshot.resourceId.source, snapshot.resourceId.value, snapshot.title, snapshot.artist, snapshot.durationMs)
            })
        }
        val musicSource = when (source) {
            LyricAutoSource.QQMusic -> MusicSource.QQMusic
            LyricAutoSource.Netease -> MusicSource.Netease
            else -> return ResolvedLyrics.Empty
        }
        if (musicSource == snapshot.resourceId.source) {
            val document = loadCurrentProvider(appContext, snapshot)
            return ResolvedLyrics(document, document.takeIf { it.lines.isNotEmpty() }?.let {
                LyricBinding(BoundLyricSource.Provider, musicSource, snapshot.resourceId.value, snapshot.title, snapshot.artist, snapshot.durationMs)
            })
        }
        val registry = MeloXMusicProviders.create(appContext)
        val provider = registry.require(musicSource)
        val search = provider as? SearchCapability ?: return ResolvedLyrics.Empty
        val lyrics = provider as? LyricsCapability ?: return ResolvedLyrics.Empty
        val queries = buildList {
            val clean = snapshot.title.replace(Regex("[（(].*?[）)]"), "").trim()
            if (clean.isNotBlank()) add(clean)
            add(snapshot.title)
            snapshot.artist.substringBefore(" /").takeIf(String::isNotBlank)?.let { artist ->
                add("$clean $artist".trim())
            }
        }.distinct()
        for (query in queries) {
            val results = runCatching { search.searchSongs(query, 1, 10).items }.getOrDefault(emptyList())
            val match = results
                .filter { candidate ->
                    isSafeCrossProviderLyricMatch(
                        targetTitle = snapshot.title,
                        targetArtist = snapshot.artist,
                        targetDurationMs = snapshot.durationMs,
                        candidate = candidate,
                    )
                }
                .maxByOrNull { candidate -> trackMatchScore(snapshot, candidate) }
                ?: continue
            val document = runCatching { lyrics.lyrics(match) }.getOrNull()
            if (document != null && document.lines.isNotEmpty()) {
                return ResolvedLyrics(
                    document,
                    LyricBinding(BoundLyricSource.Provider, musicSource, match.id.value, match.title, match.artistText, match.durationMs ?: snapshot.durationMs),
                )
            }
        }
        return ResolvedLyrics.Empty
    }

    private suspend fun loadBinding(appContext: Context, binding: LyricBinding): LyricsDocument {
        if (binding.source == BoundLyricSource.AmlL) {
            val id = binding.resourceValue.toLongOrNull() ?: return LyricsDocument(emptyList())
            return runCatching { AmlldbLyricsClient().lyrics(id) }.getOrDefault(LyricsDocument(emptyList()))
        }
        val providerSource = binding.provider ?: return LyricsDocument(emptyList())
        val provider = MeloXMusicProviders.create(appContext).require(providerSource)
        val lyrics = provider as? LyricsCapability ?: return LyricsDocument(emptyList())
        val track = MusicTrack(
            id = MusicResourceId(providerSource, binding.resourceValue),
            title = binding.title,
            artists = binding.artist.split(Regex("\\s*(?:、|/|&|,|;|；)\\s*")).filter(String::isNotBlank).map { MusicArtistRef(name = it) },
            durationMs = binding.durationMs.takeIf { it > 0L },
        )
        return runCatching { lyrics.lyrics(track) }.getOrDefault(LyricsDocument(emptyList()))
    }

    private suspend fun findMatchedNeteaseTrack(appContext: Context, snapshot: LyricTrackSnapshot): MusicTrack? {
        val provider = MeloXMusicProviders.create(appContext).require(MusicSource.Netease)
        val search = provider as? SearchCapability ?: return null
        val queries = listOf(snapshot.title, "${snapshot.title} ${snapshot.artist.substringBefore(" /")}").distinct()
        for (query in queries) {
            val match = runCatching { search.searchSongs(query, 1, 10).items }.getOrDefault(emptyList())
                .filter { isSafeCrossProviderLyricMatch(snapshot.title, snapshot.artist, snapshot.durationMs, it) }
                .maxByOrNull { trackMatchScore(snapshot, it) }
            if (match != null) return match
        }
        return null
    }

    private suspend fun loadCurrentProvider(
        appContext: Context,
        snapshot: LyricTrackSnapshot,
    ): LyricsDocument {
        val resourceId = snapshot.resourceId
        if (resourceId.source == MusicSource.Netease) {
            val songId = resourceId.value.toLongOrNull()
                ?: return LyricsDocument(emptyList())
            return MeloXDownloadStore.get(appContext).localLyrics(songId)
                ?: NeteaseSearchClient(
                    cookieProvider = { NeteaseSessionStore.readCookie(appContext) },
                ).lyrics(songId)
        }

        val provider = MeloXMusicProviders.create(appContext).require(resourceId.source)
        val lyricCapability = provider as? LyricsCapability
            ?: return LyricsDocument(emptyList())
        val artistRefs = snapshot.artist
            .split(Regex("\\s*(?:、|/|&|,|;|；)\\s*"))
            .map(String::trim)
            .filter(String::isNotBlank)
            .ifEmpty { listOf("未知歌手") }
            .map { MusicArtistRef(name = it) }
        val album = snapshot.album.takeIf(String::isNotBlank)?.let {
            MusicAlbumRef(
                name = it,
                artworkUrl = snapshot.artworkUrl,
            )
        }
        val track = MusicTrack(
            id = resourceId,
            title = snapshot.title.ifBlank { "未知歌曲" },
            artists = artistRefs,
            album = album,
            artworkUrl = snapshot.artworkUrl,
            durationMs = snapshot.durationMs.takeIf { it > 0L },
        )
        return lyricCapability.lyrics(track)
    }

    private fun trackMatchScore(snapshot: LyricTrackSnapshot, candidate: MusicTrack): Int {
        val artist = normalizeLyricMatchText(snapshot.artist)
        val candidateArtist = normalizeLyricMatchText(candidate.artistText)
        return 100 +
            (if (artist.isNotBlank() && (candidateArtist.contains(artist) || artist.contains(candidateArtist))) 25 else 0) -
            ((candidate.durationMs?.let { kotlin.math.abs(snapshot.durationMs - it) } ?: 0L) / 1_000L).toInt()
    }

    private data class LyricTrackSnapshot(
        val resourceId: MusicResourceId,
        val title: String,
        val artist: String,
        val album: String,
        val artworkUrl: String?,
        val durationMs: Long,
        val automaticSelection: Boolean,
        val binding: LyricBinding?,
    ) {
        fun cacheKey(): String =
            "${if (automaticSelection) "auto-v$AutomaticSelectionCacheVersion" else "provider"}:${resourceId.source.storageValue}:${resourceId.value}" +
                binding?.let { ":bound:${it.stableKey()}" }.orEmpty()
    }

    private data class ResolvedLyrics(val document: LyricsDocument, val binding: LyricBinding?) {
        companion object { val Empty = ResolvedLyrics(LyricsDocument(emptyList()), null) }
    }
}

internal data class AutoLyricCandidate(val priority: Int, val document: LyricsDocument, val binding: LyricBinding? = null)

private enum class LyricAutoSource { AmlL, QQMusic, Netease, Current }

internal fun selectAutomaticLyrics(candidates: List<AutoLyricCandidate>): LyricsDocument? =
    selectAutomaticLyricCandidate(candidates)?.document

internal fun selectAutomaticLyricCandidate(candidates: List<AutoLyricCandidate>): AutoLyricCandidate? =
    candidates
        .filter { it.document.lines.isNotEmpty() && hasWordTiming(it.document) }
        .minByOrNull(AutoLyricCandidate::priority)
        ?: candidates
            .filter { it.document.lines.isNotEmpty() }
            .minByOrNull(AutoLyricCandidate::priority)

private fun hasWordTiming(document: LyricsDocument): Boolean =
    document.lines.any { line -> line.syllables.isNotEmpty() }

private fun lyricQualityScore(document: LyricsDocument): Int =
    document.lines.count { it.syllables.isNotEmpty() } * 100 +
        document.lines.count { !it.translation.isNullOrBlank() } * 10 +
        document.lines.count { !it.romanization.isNullOrBlank() } * 5 +
        document.lines.size

internal enum class LyricTrackVersion { Original, Live, Dj, Remix, Instrumental }

internal data class LyricTitleIdentity(
    val baseTitle: String,
    val version: LyricTrackVersion,
    val versionLabel: String,
)

internal fun lyricTitleIdentity(title: String): LyricTitleIdentity {
    val normalized = title.lowercase()
    val version = when {
        Regex("(?:^|[^a-z])dj(?:[^a-z]|$)|电音|舞曲").containsMatchIn(normalized) -> LyricTrackVersion.Dj
        Regex("remix|重混|混音版").containsMatchIn(normalized) -> LyricTrackVersion.Remix
        Regex("live|现场|演唱会|音乐节").containsMatchIn(normalized) -> LyricTrackVersion.Live
        Regex("instrumental|伴奏|纯音乐|off vocal|karaoke").containsMatchIn(normalized) -> LyricTrackVersion.Instrumental
        else -> LyricTrackVersion.Original
    }
    val versionPattern = Regex(
        "(?i)(?:[（(【\\[]?\\s*(?:dj(?:\\s+version)?|[^）)】\\]]*remix|live|现场版?|演唱会版?|音乐节版?|instrumental|伴奏版?|纯音乐|off\\s*vocal|karaoke)\\s*[）)】\\]]?)",
    )
    val base = normalizeLyricMatchText(title.replace(versionPattern, ""))
    val label = normalizeLyricMatchText(title).removePrefix(base)
    return LyricTitleIdentity(base, version, label)
}

internal fun isSafeCrossProviderLyricMatch(
    targetTitle: String,
    targetArtist: String,
    targetDurationMs: Long,
    candidate: MusicTrack,
): Boolean {
    val target = lyricTitleIdentity(targetTitle)
    val other = lyricTitleIdentity(candidate.title)
    if (target.baseTitle.isBlank() || target.baseTitle != other.baseTitle) return false
    if (target.version != other.version) return false
    if (target.version != LyricTrackVersion.Original && target.versionLabel != other.versionLabel) return false
    val candidateDuration = candidate.durationMs ?: return false
    val toleranceMs = if (target.version == LyricTrackVersion.Original) 2_000L else 1_000L
    if (kotlin.math.abs(targetDurationMs - candidateDuration) > toleranceMs) return false
    val targetArtistKey = normalizeLyricMatchText(targetArtist.substringBefore(" /"))
    val candidateArtistKey = normalizeLyricMatchText(candidate.artistText)
    return targetArtistKey.isBlank() || candidateArtistKey.contains(targetArtistKey) || targetArtistKey.contains(candidateArtistKey)
}

internal fun normalizeLyricMatchText(value: String): String = value.lowercase()
    .replace(Regex("[^\\p{L}\\p{N}]"), "")
