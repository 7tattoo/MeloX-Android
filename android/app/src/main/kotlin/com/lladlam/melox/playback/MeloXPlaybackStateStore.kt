package com.lladlam.melox.playback

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

/**
 * 播放状态持久化：记录上次播放的队列、当前歌曲索引、播放位置与播放/暂停状态，
 * 供服务重启后恢复。JSON 存于 SharedPreferences，结构简单、可人工检查。
 */
object MeloXPlaybackStateStore {
    private const val NAME = "melox_playback_state"
    private const val KEY_STATE = "state_json"
    const val KEY_AT = "at"
    const val KEY_SONG_IDS = "songIds"
    const val KEY_INDEX = "index"
    const val KEY_POSITION_MS = "positionMs"
    const val KEY_PLAY_WHEN_READY = "playWhenReady"
    /** 快照超过该时长（24 小时）视为过期，不再恢复 */
    const val SNAPSHOT_MAX_AGE_MS = 24L * 60L * 60L * 1000L

    /** 播放状态快照 */
    data class Snapshot(
        val songIds: List<Long>,
        val index: Int,
        val positionMs: Long,
        val playWhenReady: Boolean,
        val at: Long,
    ) {
        fun isValid(): Boolean =
            songIds.isNotEmpty() &&
                index in 0 until songIds.size &&
                System.currentTimeMillis() - at <= SNAPSHOT_MAX_AGE_MS
    }

    private fun preferences(context: Context) =
        context.applicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    fun save(context: Context, snapshot: Snapshot) {
        if (snapshot.songIds.isEmpty()) return
        runCatching {
            val ids = JSONArray()
            snapshot.songIds.forEach { ids.put(it) }
            val json = JSONObject()
                .put(KEY_SONG_IDS, ids)
                .put(KEY_INDEX, snapshot.index)
                .put(KEY_POSITION_MS, snapshot.positionMs)
                .put(KEY_PLAY_WHEN_READY, snapshot.playWhenReady)
                .put(KEY_AT, snapshot.at)
            preferences(context).edit().putString(KEY_STATE, json.toString()).apply()
        }
    }

    fun load(context: Context): Snapshot? = runCatching {
        val raw = preferences(context).getString(KEY_STATE, null) ?: return null
        val json = JSONObject(raw)
        val idsArray = json.optJSONArray(KEY_SONG_IDS) ?: return null
        val songIds = buildList {
            for (i in 0 until idsArray.length()) idsArray.optLong(i).takeIf { it > 0L }?.let(::add)
        }
        if (songIds.isEmpty()) return null
        Snapshot(
            songIds = songIds,
            index = json.optInt(KEY_INDEX, 0),
            positionMs = json.optLong(KEY_POSITION_MS, 0L).coerceAtLeast(0L),
            playWhenReady = json.optBoolean(KEY_PLAY_WHEN_READY, true),
            at = json.optLong(KEY_AT, 0L),
        )
    }.getOrNull()

    fun clear(context: Context) {
        preferences(context).edit().remove(KEY_STATE).apply()
    }
}
