package com.lladlam.melox.core.provider.bilibili

import android.content.Context
import org.json.JSONObject

data class BilibiliPlaybackAssociation(
    val originalBvid: String,
    val originalCid: Long,
    val replacementBvid: String,
    val replacementCid: Long,
    val title: String,
    val consensusDurationMs: Long,
    val replacementCatalogDurationMs: Long?,
    val algorithmVersion: Int = BilibiliPlaybackAssociationStore.AlgorithmVersion,
)

object BilibiliPlaybackAssociationStore {
    const val AlgorithmVersion = 2
    private const val PreferencesName = "melox_bilibili_playback_associations"
    private const val RevisionKey = "__revision"

    fun read(context: Context, bvid: String, cid: Long): BilibiliPlaybackAssociation? {
        val raw = preferences(context).getString(key(bvid, cid), null) ?: return null
        return runCatching {
            JSONObject(raw).let { json ->
                if (json.optInt("algorithmVersion", 0) != AlgorithmVersion) return null
                BilibiliPlaybackAssociation(
                    bvid, cid, json.getString("replacementBvid"), json.getLong("replacementCid"),
                    json.optString("title"), json.optLong("consensusDurationMs"),
                    json.optLong("replacementCatalogDurationMs").takeIf { it > 0 }
                        ?: json.optLong("measuredDurationMs").takeIf { it > 0 },
                    json.optInt("algorithmVersion", AlgorithmVersion),
                )
            }
        }.getOrNull()
    }

    fun write(context: Context, association: BilibiliPlaybackAssociation) {
        writeIfChanged(context, association)
    }

    fun writeIfChanged(context: Context, association: BilibiliPlaybackAssociation): Boolean {
        val current = read(context, association.originalBvid, association.originalCid)
        if (current?.replacementBvid == association.replacementBvid &&
            current.replacementCid == association.replacementCid &&
            current.algorithmVersion == association.algorithmVersion
        ) return false
        val json = JSONObject()
            .put("replacementBvid", association.replacementBvid)
            .put("replacementCid", association.replacementCid)
            .put("title", association.title)
            .put("consensusDurationMs", association.consensusDurationMs)
            .put("replacementCatalogDurationMs", association.replacementCatalogDurationMs)
            .put("algorithmVersion", association.algorithmVersion)
        mutate(context) { putString(key(association.originalBvid, association.originalCid), json.toString()) }
        return true
    }

    fun remove(context: Context, bvid: String, cid: Long) = mutate(context) { remove(key(bvid, cid)) }
    fun clear(context: Context) = mutate(context) { clear() }
    fun revision(context: Context): Long = preferences(context).getLong(RevisionKey, 0L)

    private fun mutate(context: Context, mutation: android.content.SharedPreferences.Editor.() -> Unit) {
        val preferences = preferences(context)
        preferences.edit().apply(mutation).putLong(RevisionKey, preferences.getLong(RevisionKey, 0L) + 1L).apply()
    }
    private fun preferences(context: Context) = context.applicationContext.getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
    private fun key(bvid: String, cid: Long) = "$bvid:$cid"
}
