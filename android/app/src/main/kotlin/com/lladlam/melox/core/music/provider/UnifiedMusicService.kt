package com.lladlam.melox.core.music.provider

import com.lladlam.melox.core.music.model.MusicSource
import com.lladlam.melox.core.music.model.MusicTrack
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

/**
 * Explicit opt-in cross-provider search.
 *
 * This class deliberately does not decide which providers participate. The caller
 * must pass the already-approved source whitelist from [MusicProviderSelectionStore].
 * It also does not perform automatic playback fallback or try to bypass provider
 * availability/rights: results keep their original provider identity.
 */
class UnifiedMusicService(
    private val registry: MusicProviderRegistry,
) {
    data class SearchFailure(
        val source: MusicSource,
        val message: String,
    )

    data class SearchResult(
        val tracks: List<MusicTrack>,
        val failures: List<SearchFailure> = emptyList(),
    )

    suspend fun searchSongs(
        query: String,
        sources: Set<MusicSource>,
        page: Int = 1,
        pageSizePerProvider: Int = 20,
    ): SearchResult = coroutineScope {
        val normalized = query.trim()
        if (normalized.isEmpty() || sources.isEmpty()) return@coroutineScope SearchResult(emptyList())

        val orderedSources = MusicSource.entries.filter(sources::contains)
        val requests = orderedSources.map { source ->
            async {
                val provider = registry[source]
                val search = provider as? SearchCapability
                if (search == null) {
                    ProviderResult(
                        source = source,
                        tracks = emptyList(),
                        failure = SearchFailure(source, "${source.displayName} 当前没有搜索能力"),
                    )
                } else {
                    runCatching {
                        search.searchSongs(
                            query = normalized,
                            page = page.coerceAtLeast(1),
                            pageSize = pageSizePerProvider.coerceIn(1, 50),
                        ).items
                    }.fold(
                        onSuccess = { tracks -> ProviderResult(source, tracks, null) },
                        onFailure = { error ->
                            ProviderResult(
                                source = source,
                                tracks = emptyList(),
                                failure = SearchFailure(
                                    source,
                                    error.message ?: "${source.displayName} 搜索失败",
                                ),
                            )
                        },
                    )
                }
            }
        }.awaitAll()

        // Keep provider results distinct. Same-title tracks are not silently
        // merged here because their rights/quality/account availability differ.
        SearchResult(
            tracks = requests.flatMap(ProviderResult::tracks),
            failures = requests.mapNotNull(ProviderResult::failure),
        )
    }

    private data class ProviderResult(
        val source: MusicSource,
        val tracks: List<MusicTrack>,
        val failure: SearchFailure?,
    )
}
