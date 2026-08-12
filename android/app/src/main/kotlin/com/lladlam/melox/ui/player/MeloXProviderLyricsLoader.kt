package com.lladlam.melox.ui.player

import android.content.Context
import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.download.MeloXDownloadStore
import com.lladlam.melox.core.lyrics.LyricsDocument
import com.lladlam.melox.core.music.model.MusicAlbumRef
import com.lladlam.melox.core.music.model.MusicArtistRef
import com.lladlam.melox.core.music.model.MusicResourceId
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import com.lladlam.melox.core.music.provider.LyricsCapability
import com.lladlam.melox.core.music.provider.MeloXMusicProviders
import com.lladlam.melox.core.network.NeteaseSearchClient
import com.lladlam.melox.playback.PlaybackTrackIdentity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async

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
    private val workerScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val lock = Any()
    private val inFlight = mutableMapOf<String, Deferred<LyricsDocument>>()
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
        val cacheKey = "${resourceId.source.storageValue}:${resourceId.value}"
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
        )

        val deferred = synchronized(lock) {
            cache[cacheKey]?.let { return it }
            inFlight[cacheKey] ?: workerScope.async {
                loadDocument(appContext, snapshot).also { document ->
                    // Do not permanently cache an empty document produced while
                    // provider metadata was still settling; a later attempt may
                    // have enough title/duration information to resolve it.
                    if (document.lines.isNotEmpty()) remember(cacheKey, document)
                }
            }.also { created ->
                inFlight[cacheKey] = created
            }
        }

        return try {
            deferred.await()
        } finally {
            if (deferred.isCompleted) {
                synchronized(lock) {
                    if (inFlight[cacheKey] === deferred) inFlight.remove(cacheKey)
                }
            }
        }
    }

    private suspend fun loadDocument(
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

    private data class LyricTrackSnapshot(
        val resourceId: MusicResourceId,
        val title: String,
        val artist: String,
        val album: String,
        val artworkUrl: String?,
        val durationMs: Long,
    )
}
