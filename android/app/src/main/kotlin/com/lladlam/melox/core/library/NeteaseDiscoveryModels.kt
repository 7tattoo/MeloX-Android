package com.lladlam.melox.core.library

import com.lladlam.melox.core.model.SearchSong

data class NeteaseHomeContent(
    val playlists: List<NeteasePlaylistSummary>,
    val newSongs: List<SearchSong>,
)
