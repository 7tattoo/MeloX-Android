package com.lladlam.melox.core.network

import com.lladlam.melox.core.account.NeteaseSessionStore
import java.io.IOException
import java.net.URLEncoder
import java.security.MessageDigest
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

/** Small shared authenticated EAPI transport for feature modules that need session-scoped calls. */
internal class NeteaseAuthenticatedEapi(
    private val cookieProvider: () -> String,
    private val httpClient: OkHttpClient = OkHttpClient(),
) {
    private val syntheticDeviceId = randomHex(26).uppercase()

    fun post(uri: String, data: JSONObject = JSONObject()): JSONObject {
        val cookie = cookieProvider()
        if (!NeteaseSessionStore.containsMusicU(cookie)) throw IOException("请先登录网易云音乐")

        val now = System.currentTimeMillis()
        val cookies = NeteaseSessionStore.parseCookie(cookie)
        val header = authenticatedHeader(cookies, now)
        val payload = JSONObject(data.toString()).put("header", header).put("e_r", false)
        val json = payload.toString()
        val digest = md5Hex("nobody${uri}use${json}md5forencrypt")
        val encrypted = "$uri-36cd479b6b5-$json-36cd479b6b5-$digest"
        val params = aes(encrypted.toByteArray(), "e82ckenh8dichen8".toByteArray()).toHex()

        val request = Request.Builder()
            .url("https://interface.music.163.com${uri.replace("/api/", "/eapi/")}")
            .header("Accept", "*/*")
            .header("User-Agent", "NeteaseMusic 9.0.90/5038 (iPhone; iOS 16.2; zh_CN)")
            .header("Cookie", encodedCookie(header))
            .post(FormBody.Builder().add("params", params).build())
            .build()

        httpClient.newCall(request).execute().use { response ->
            val body = response.body.string()
            if (!response.isSuccessful) throw IOException("网易云请求失败：HTTP ${response.code}")
            if (body.isBlank()) throw IOException("网易云返回了空响应")
            val result = JSONObject(body)
            val code = result.optInt("code", response.code)
            if (code !in 200..299) {
                throw IOException(
                    result.optString("message")
                        .ifBlank { result.optString("msg") }
                        .ifBlank { "请求失败（$code）" },
                )
            }
            return result
        }
    }

    private fun authenticatedHeader(cookies: Map<String, String>, now: Long) = JSONObject()
        .put("osver", cookies["osver"] ?: "16.2")
        .put("deviceId", cookies["deviceId"] ?: syntheticDeviceId)
        .put("os", cookies["os"] ?: "iPhone OS")
        .put("appver", cookies["appver"] ?: "9.0.90")
        .put("versioncode", cookies["versioncode"] ?: "140")
        .put("buildver", cookies["buildver"] ?: (now / 1000L).toString())
        .put("resolution", cookies["resolution"] ?: "1170x2532")
        .put("__csrf", cookies["__csrf"] ?: "")
        .put("channel", cookies["channel"] ?: "distribution")
        .put("requestId", "${now}_${randomDigits(4)}")
        .apply {
            cookies["MUSIC_U"]?.takeIf(String::isNotBlank)?.let { put("MUSIC_U", it) }
        }

    private fun encodedCookie(value: JSONObject): String = buildList {
        val keys = value.keys()
        while (keys.hasNext()) add(keys.next())
    }.sorted().joinToString("; ") { key -> "${encode(key)}=${encode(value.optString(key))}" }

    private fun encode(value: String) = URLEncoder.encode(value, Charsets.UTF_8.name()).replace("+", "%20")

    private fun randomHex(count: Int): String {
        val bytes = ByteArray(count)
        SecureRandom().nextBytes(bytes)
        return bytes.joinToString("") { "%02x".format(it) }
    }

    private fun randomDigits(count: Int) = buildString(count) {
        repeat(count) { append(('0'.code + SecureRandom().nextInt(10)).toChar()) }
    }

    private fun md5Hex(value: String) = MessageDigest.getInstance("MD5")
        .digest(value.toByteArray())
        .joinToString("") { "%02x".format(it) }

    private fun aes(data: ByteArray, key: ByteArray) = Cipher.getInstance("AES/ECB/PKCS5Padding").run {
        init(Cipher.ENCRYPT_MODE, SecretKeySpec(key, "AES"))
        doFinal(data)
    }

    private fun ByteArray.toHex() = joinToString("") { "%02X".format(it) }
}
