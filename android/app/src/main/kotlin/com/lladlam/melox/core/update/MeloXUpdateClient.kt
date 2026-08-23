package com.lladlam.melox.core.update

import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

data class MeloXRelease(
    val version: String,
    val name: String,
    val notes: String,
    val pageUrl: String,
    val apkUrl: String?,
    val apkName: String?,
    val publishedAt: String,
)

enum class MeloXUpdateDownloadSource { Auto, GitHub, JsDelivr }

class MeloXUpdateClient(private val httpClient: OkHttpClient = com.lladlam.melox.core.network.MeloXHttpClient.shared) {
    suspend fun latestStableRelease(): MeloXRelease = withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url("https://api.github.com/repos/lladlam/MeloX-Android/releases?per_page=30")
            .header("Accept", "application/vnd.github+json")
            .header("X-GitHub-Api-Version", "2022-11-28")
            .header("User-Agent", "MeloX-Android")
            .build()
        httpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("更新检查失败：HTTP ${response.code}")
            val releases = JSONArray(response.body.string())
            for (index in 0 until releases.length()) {
                val release = releases.optJSONObject(index) ?: continue
                if (release.optBoolean("draft") || release.optBoolean("prerelease")) continue
                val tag = release.optString("tag_name").trim()
                val page = release.optString("html_url").trim()
                if (tag.isBlank() || page.isBlank()) continue
                val assets = release.optJSONArray("assets") ?: JSONArray()
                var apk: String? = null
                var apkName: String? = null
                for (assetIndex in 0 until assets.length()) {
                    val asset = assets.optJSONObject(assetIndex) ?: continue
                    val name = asset.optString("name")
                    if (name.endsWith(".apk", ignoreCase = true)) {
                        apk = asset.optString("browser_download_url").takeIf(String::isNotBlank)
                        apkName = name
                        break
                    }
                }
                return@withContext MeloXRelease(
                    version = tag,
                    name = release.optString("name").ifBlank { tag },
                    notes = release.optString("body"),
                    pageUrl = page,
                    apkUrl = apk,
                    apkName = apkName,
                    publishedAt = release.optString("published_at"),
                )
            }
            throw IOException("当前仓库还没有正式发布版本")
        }
    }

    suspend fun downloadUrl(
        release: MeloXRelease,
        source: MeloXUpdateDownloadSource,
    ): String? = withContext(Dispatchers.IO) {
        val github = release.apkUrl
        if (source == MeloXUpdateDownloadSource.GitHub) return@withContext github
        val name = release.apkName ?: return@withContext if (source == MeloXUpdateDownloadSource.Auto) github else null
        val candidates = listOf(
            "https://cdn.jsdelivr.net/gh/lladlam/MeloX-Android@${release.version}/releases/$name",
            "https://cdn.jsdelivr.net/gh/lladlam/MeloX-Android@${release.version}/$name",
        )
        candidates.firstOrNull(::isAvailable)
            ?: if (source == MeloXUpdateDownloadSource.Auto) github else null
    }

    private fun isAvailable(url: String): Boolean {
        val request = Request.Builder().url(url).head().header("User-Agent", "MeloX-Android").build()
        return runCatching { httpClient.newCall(request).execute().use { it.isSuccessful } }.getOrDefault(false)
    }

    fun isNewer(latest: String, current: String): Boolean {
        fun parts(value: String) = value.trim().removePrefix("v").substringBefore('-')
            .split('.').map { it.toIntOrNull() ?: 0 }
        val left = parts(latest)
        val right = parts(current)
        for (index in 0 until maxOf(left.size, right.size)) {
            val difference = (left.getOrElse(index) { 0 } - right.getOrElse(index) { 0 })
            if (difference != 0) return difference > 0
        }
        return false
    }
}
