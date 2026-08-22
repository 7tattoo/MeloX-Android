package com.lladlam.melox.core.provider.kugou

import com.lladlam.melox.core.lyrics.LyricsDocument
import com.lladlam.melox.core.music.model.AudioQualityTier
import com.lladlam.melox.core.music.model.MusicAccountSummary
import com.lladlam.melox.core.music.model.MusicAlbumDetail
import com.lladlam.melox.core.music.model.MusicAlbumSummary
import com.lladlam.melox.core.music.model.MusicArtistDetail
import com.lladlam.melox.core.music.model.MusicArtistSummary
import com.lladlam.melox.core.music.model.MusicHomeFeed
import com.lladlam.melox.core.music.model.MusicPage
import com.lladlam.melox.core.music.model.MusicPlaylistDetail
import com.lladlam.melox.core.music.model.MusicPlaylistSummary
import com.lladlam.melox.core.music.model.MusicRankingSummary
import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import com.lladlam.melox.core.music.model.PlaybackResolution
import com.lladlam.melox.core.music.provider.AlbumCapability
import com.lladlam.melox.core.music.provider.ArtistCapability
import com.lladlam.melox.core.music.provider.CatalogSearchCapability
import com.lladlam.melox.core.music.provider.HomeFeedCapability
import com.lladlam.melox.core.music.provider.LyricsCapability
import com.lladlam.melox.core.music.provider.LocalAggregationCapability
import com.lladlam.melox.core.music.provider.MusicCapability
import com.lladlam.melox.core.music.provider.MusicProvider
import com.lladlam.melox.core.music.provider.PlaybackCapability
import com.lladlam.melox.core.music.provider.PlaylistCapability
import com.lladlam.melox.core.music.provider.PlaylistWriteCapability
import com.lladlam.melox.core.music.provider.RankingCapability
import com.lladlam.melox.core.music.provider.SearchCapability
import com.lladlam.melox.core.music.provider.UserLibraryCapability
import okhttp3.OkHttpClient

class KugouProvider(
    sessionProvider: () -> KugouSession,
    httpClient: OkHttpClient = com.lladlam.melox.core.network.MeloXHttpClient.shared,
) : MusicProvider,
    SearchCapability,
    CatalogSearchCapability,
    LyricsCapability,
    PlaybackCapability,
    HomeFeedCapability,
    UserLibraryCapability,
    PlaylistCapability,
    PlaylistWriteCapability,
    RankingCapability,
    AlbumCapability,
    ArtistCapability,
    LocalAggregationCapability {
    override val source: MusicSource = MusicSource.Kugou
    override val displayName: String = source.displayName
    override val capabilities: Set<MusicCapability> = setOf(
        MusicCapability.Search,
        MusicCapability.Playback,
        MusicCapability.Lyrics,
        MusicCapability.Library,
        MusicCapability.Playlists,
        MusicCapability.PlaylistWrite,
        MusicCapability.Albums,
        MusicCapability.Artists,
        MusicCapability.HomeRecommendations,
        MusicCapability.Rankings,
    )

    private val api = KugouApiClient(
        sessionProvider = sessionProvider,
        httpClient = httpClient,
    )
    private val lyrics = KugouLyricsClient(
        sessionProvider = sessionProvider,
        httpClient = httpClient,
    )
    private val discovery = KugouDiscoveryClient(
        sessionProvider = sessionProvider,
        httpClient = httpClient,
    )
    private val playlists = KugouPlaylistClient(
        sessionProvider = sessionProvider,
        httpClient = httpClient,
    )
    private val playlistWrites = KugouPlaylistWriteClient(
        sessionProvider = sessionProvider,
        httpClient = httpClient,
    )
    private val rankings = KugouRankingClient(
        sessionProvider = sessionProvider,
        httpClient = httpClient,
    )
    private val catalog = KugouCatalogClient(
        sessionProvider = sessionProvider,
        httpClient = httpClient,
    )

    override suspend fun searchSongs(query: String, page: Int, pageSize: Int): MusicPage<MusicTrack> =
        api.searchSongs(query, page, pageSize)

    override suspend fun searchPlaylists(
        query: String,
        page: Int,
        pageSize: Int,
    ): MusicPage<MusicPlaylistSummary> = catalog.searchPlaylists(query, page, pageSize)

    override suspend fun searchAlbums(
        query: String,
        page: Int,
        pageSize: Int,
    ): MusicPage<MusicAlbumSummary> = catalog.searchAlbums(query, page, pageSize)

    override suspend fun searchArtists(
        query: String,
        page: Int,
        pageSize: Int,
    ): MusicPage<MusicArtistSummary> = catalog.searchArtists(query, page, pageSize)

    override suspend fun lyrics(track: MusicTrack): LyricsDocument = lyrics.lyrics(track)

    override suspend fun resolvePlayback(
        track: MusicTrack,
        quality: AudioQualityTier,
    ): PlaybackResolution = api.resolvePlayback(track, quality)

    override suspend fun homeFeed(
        playlistLimit: Int,
        newSongLimit: Int,
        rankingLimit: Int,
    ): MusicHomeFeed = discovery.homeFeed(playlistLimit, newSongLimit, rankingLimit)

    override suspend fun accountSummary(): MusicAccountSummary? = discovery.accountSummary()

    override suspend fun userPlaylists(page: Int, pageSize: Int): MusicPage<MusicPlaylistSummary> =
        discovery.userPlaylists(page, pageSize)

    override suspend fun aggregationTracks(page: Int, pageSize: Int): MusicPage<MusicTrack> {
        val playlists = discovery.userPlaylists(page = 1, pageSize = 20).items
        val tracks = playlists.flatMap { playlist ->
            runCatching { this@KugouProvider.playlistDetail(playlist, page = 1, pageSize = pageSize).tracks }
                .getOrDefault(emptyList())
        }.distinctBy { it.id.value }
        return MusicPage(tracks, page, pageSize, tracks.size.toLong())
    }

    override suspend fun playlistDetail(
        playlist: MusicPlaylistSummary,
        page: Int,
        pageSize: Int,
    ): MusicPlaylistDetail = playlists.detail(playlist, page, pageSize)

    override suspend fun writablePlaylists(page: Int, pageSize: Int): MusicPage<MusicPlaylistSummary> =
        playlistWrites.writablePlaylists(page, pageSize)

    override suspend fun addTrackToPlaylist(
        track: MusicTrack,
        playlist: MusicPlaylistSummary,
    ) {
        playlistWrites.addTrackToPlaylist(track, playlist)
    }

    override suspend fun rankingTracks(
        ranking: MusicRankingSummary,
        page: Int,
        pageSize: Int,
    ): MusicPage<MusicTrack> = rankings.tracks(ranking, page, pageSize)

    override suspend fun albumDetail(
        album: MusicAlbumSummary,
        page: Int,
        pageSize: Int,
    ): MusicAlbumDetail = catalog.albumDetail(album, page, pageSize)

    override suspend fun artistDetail(
        artist: MusicArtistSummary,
        page: Int,
        pageSize: Int,
    ): MusicArtistDetail = catalog.artistDetail(artist, page, pageSize)
}
