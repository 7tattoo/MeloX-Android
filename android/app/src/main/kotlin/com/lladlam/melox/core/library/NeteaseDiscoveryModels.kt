package com.lladlam.melox.core.library
import com.lladlam.melox.core.model.SearchSong
data class NeteaseHomePodcast(val id: Long, val name: String, val artworkUrl: String?)
data class NeteaseHomeContent(
    val playlists: List<NeteasePlaylistSummary>, val newSongs: List<SearchSong>,
    val radarPlaylists: List<NeteasePlaylistSummary> = emptyList(), val personalPlaylists: List<NeteasePlaylistSummary> = emptyList(),
    val regionalSongs: List<SearchSong> = emptyList(), val roamingSongs: List<SearchSong> = emptyList(),
    val similarSongs: List<SearchSong> = emptyList(), val podcasts: List<NeteaseHomePodcast> = emptyList(),
)
