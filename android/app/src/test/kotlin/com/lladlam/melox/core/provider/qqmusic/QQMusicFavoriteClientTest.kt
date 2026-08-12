package com.lladlam.melox.core.provider.qqmusic

import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class QQMusicFavoriteClientTest {
    private val client = QQMusicFavoriteClient(
        sessionProvider = { QQMusicSession("", "", "") },
    )

    @Test
    fun resolvesNumericSongIdAndTypeFromTrackInfo() {
        val response = JSONObject().put(
            "tracks",
            JSONArray().put(
                JSONObject()
                    .put("mid", "003ABC")
                    .put("id", 123456789L)
                    .put("type", 1),
            ),
        )
        val ref = client.parseWriteRef(response, "003ABC")
        assertNotNull(ref)
        assertEquals(123456789L, ref!!.songId)
        assertEquals(1, ref.songType)
    }

    @Test
    fun favoriteWritesToFixedLikedDirectory201() {
        val param = client.buildWriteParam(
            QQMusicFavoriteClient.SongWriteRef(
                songId = 123L,
                songType = 0,
            ),
        )
        assertEquals(201, param.getInt("dirId"))
        assertEquals(0, param.getInt("tid"))
        val song = param.getJSONArray("v_songInfo").getJSONObject(0)
        assertEquals(123L, song.getLong("songId"))
        assertEquals(0, song.getInt("songType"))
    }

    @Test
    fun mismatchedMidIsNotUsedForFavoriteWrite() {
        val response = JSONObject().put(
            "tracks",
            JSONArray().put(
                JSONObject()
                    .put("mid", "OTHER")
                    .put("id", 999L)
                    .put("type", 0),
            ),
        )
        assertEquals(null, client.parseWriteRef(response, "EXPECTED"))
    }
}
