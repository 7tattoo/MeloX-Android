package com.lladlam.melox.ui.player

import android.content.Context
import com.lladlam.melox.core.account.NeteaseSessionStore
import com.lladlam.melox.core.download.MeloXDownloadStore
import com.lladlam.melox.core.lyrics.LyricsDocument
import com.lladlam.melox.core.music.model.MusicAlbumRef
import com.lladlam.melox.core.music.model.MusicArtistRef
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import com.lladlam.melox.core.music.provider.LyricsCapability
import com.lladlam.melox.core.music.provider.MeloXMusicProviders
import com.lladlam.melox.core.network.NeteaseSearchClient
import com.lladlam.melox.playback.PlaybackTrackIdentity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Single Now Playing lyric data entry point for every visual lyric style.
 *
 * Network/decryption work is kept outside the lyric render loop. A small in-memory
 * LRU cache also lets Apple Music / EVA / TextPV / Skyline reuse the exact same
 * [LyricsDocument] instead of fetching and parsing it again when styles switch or
 * the player scene is reconstructed.
 */
internal object MeloXProviderLyricsLoader {
    private const val MaxCachedDocuments = 24
    private val cache = object : LinkedHashMap<String, LyricsDocument>(MaxCachedDocuments, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, LyricsDocument>?): Boolean =
            size > MaxCachedDocuments
    }

    @Synchronized
    private fun cached(key: String): LyricsDocument? = cache[key]

    @Synchronized
    private fun remember(key: String, document: LyricsDocument): LyricsDocument {
        cache[key] = document
        return document
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

        return withContext(Dispatchers.IO) {
            cached(cacheKey)?.let { return@withContext it }

            val document = if (resourceId.source == MusicSource.Netease) {
                val songId = resourceId.value.toLongOrNull()
                    ?: return@withContext LyricsDocument(emptyList())
                MeloXDownloadStore.get(appContext).localLyrics(songId)
                    ?: NeteaseSearchClient(
                        cookieProvider = { NeteaseSessionStore.readCookie(appContext) },
                    ).lyrics(songId)
            } else {
                val provider = MeloXMusicProviders.create(appContext).require(resourceId.source)
                val lyricCapability = provider as? LyricsCapability
                    ?: return@withContext LyricsDocument(emptyList())
                val artistRefs = state.artist
                    .split(Regex("\\s*(?:、|/|&|,|;|；)\\s*"))
                    .map(String::trim)
                    .filter(String::isNotBlank)
                    .ifEmpty { listOf("未知歌手") }
                    .map { MusicArtistRef(name = it) }
                val album = state.album.takeIf(String::isNotBlank)?.let {
                    MusicAlbumRef(
                        name = it,
                        artworkUrl = state.artworkUrl,
                    )
                }
                val track = MusicTrack(
                    id = resourceId,
                    title = state.title.ifBlank { "未知歌曲" },
                    artists = artistRefs,
                    album = album,
                    artworkUrl = state.artworkUrl,
                    durationMs = state.durationMs.takeIf { it > 0L },
                )
                lyricCapability.lyrics(track)
            }

            remember(cacheKey, document)
        }
    }
}
