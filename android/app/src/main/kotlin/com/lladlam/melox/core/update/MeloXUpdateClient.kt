package com.lladlam.melox.core.update

data class MeloXRelease(
    val version: String = "",
    val name: String = "",
    val notes: String = "",
    val pageUrl: String = "",
    val apkUrl: String? = null,
    val apkName: String? = null,
    val publishedAt: String = "",
)

enum class MeloXUpdateDownloadSource { Auto, GitHub, JsDelivr }

/** 已移除更新功能，所有方法返回空值。 */
class MeloXUpdateClient {
    suspend fun latestStableRelease(): MeloXRelease = MeloXRelease()
    suspend fun downloadUrl(release: MeloXRelease, source: MeloXUpdateDownloadSource): String? = null
    fun isNewer(latest: String, current: String): Boolean = false
}