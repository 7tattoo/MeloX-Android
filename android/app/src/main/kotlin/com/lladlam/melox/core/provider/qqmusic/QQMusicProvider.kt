package com.lladlam.melox.core.provider.qqmusic

import com.lladlam.melox.core.lyrics.LyricsDocument
import com.lladlam.melox.core.music.model.AudioQualityTier
import com.lladlam.melox.core.music.model.MusicAccountSummary
import com.lladlam.melox.core.music.model.MusicHomeFeed
import com.lladlam.melox.core.music.model.MusicPage
import com.lladlam.melox.core.music.model.MusicPlaylistDetail
import com.lladlam.melox.core.music.model.MusicPlaylistSummary
import com.lladlam.melox.core.music.model.MusicRankingSummary
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import com.lladlam.melox.core.music.model.PlaybackResolution
import com.lladlam.melox.core.music.provider.HomeFeedCapability
import com.lladlam.melox.core.music.provider.LyricsCapability
import com.lladlam.melox.core.music.provider.MusicCapability
import com.lladlam.melox.core.music.provider.MusicProvider
import com.lladlam.melox.core.music.provider.PlaybackCapability
import com.lladlam.melox.core.music.provider.PlaylistCapability
import com.lladlam.melox.core.music.provider.RankingCapability
import com.lladlam.melox.core.music.provider.SearchCapability
import com.lladlam.melox.core.music.provider.UserLibraryCapability
import okhttp3.OkHttpClient

class QQMusicProvider(
    sessionProvider: () -> QQMusicSession = { QQMusicSession("", "", "") },
    httpClient: OkHttpClient = OkHttpClient(),
) : MusicProvider,
    SearchCapability,
    LyricsCapability,
    PlaybackCapability,
    HomeFeedCapability,
    UserLibraryCapability,
    PlaylistCapability,
    RankingCapability {
    override val source: MusicSource = MusicSource.QQMusic
    override val displayName: String = source.displayName
    override val capabilities: Set<MusicCapability> = setOf(
        MusicCapability.Search,
        MusicCapability.Playback,
        MusicCapability.Lyrics,
        MusicCapability.Library,
        MusicCapability.Playlists,
        MusicCapability.HomeRecommendations,
        MusicCapability.Rankings,
    )

    private val api = QQMusicApiClient(
        sessionProvider = sessionProvider,
        httpClient = httpClient,
    )
    private val richLyrics = QQMusicRichLyricsClient(
        sessionProvider = sessionProvider,
        httpClient = httpClient,
    )
    private val playlists = QQMusicPlaylistClient(
        sessionProvider = sessionProvider,
        httpClient = httpClient,
    )
    private val rankings = QQMusicRankingClient(
        sessionProvider = sessionProvider,
        httpClient = httpClient,
    )

    override suspend fun searchSongs(query: String, page: Int, pageSize: Int): MusicPage<MusicTrack> =
        api.searchSongs(query, page, pageSize)

    override suspend fun lyrics(track: MusicTrack): LyricsDocument =
        runCatching { richLyrics.lyrics(track) }
            .getOrElse { api.lyrics(track) }

    override suspend fun resolvePlayback(
        track: MusicTrack,
        quality: AudioQualityTier,
    ): PlaybackResolution = api.resolvePlayback(track, quality)

    override suspend fun homeFeed(
        playlistLimit: Int,
        newSongLimit: Int,
        rankingLimit: Int,
    ): MusicHomeFeed = api.homeFeed(playlistLimit, newSongLimit, rankingLimit)

    override suspend fun accountSummary(): MusicAccountSummary? = api.accountSummary()

    override suspend fun userPlaylists(page: Int, pageSize: Int): MusicPage<MusicPlaylistSummary> =
        api.userPlaylists(page, pageSize)

    override suspend fun playlistDetail(
        playlist: MusicPlaylistSummary,
        page: Int,
        pageSize: Int,
    ): MusicPlaylistDetail = playlists.detail(playlist, page, pageSize)

    override suspend fun rankingTracks(
        ranking: MusicRankingSummary,
        page: Int,
        pageSize: Int,
    ): MusicPage<MusicTrack> = rankings.tracks(ranking, page, pageSize)
}
