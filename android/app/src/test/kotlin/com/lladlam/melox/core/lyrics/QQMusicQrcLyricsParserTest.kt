package com.lladlam.melox.core.lyrics

import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.util.zip.DeflaterOutputStream
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class QQMusicQrcLyricsParserTest {
    @Test
    fun decryptsThePublishedThreePassQrcTransform() {
        val qrc = "[0,1000]你(0,500)好(500,500)"
        val compressed = ByteArrayOutputStream().use { output ->
            DeflaterOutputStream(output).use { it.write(qrc.toByteArray()) }
            output.toByteArray()
        }
        val padded = compressed.copyOf((compressed.size + 7) / 8 * 8)
        val encrypted = des(
            des(
                des(padded, "!@#)(*$%", Cipher.ENCRYPT_MODE),
                "123ZXC!@",
                Cipher.DECRYPT_MODE,
            ),
            "!@#)(NHL",
            Cipher.ENCRYPT_MODE,
        ).joinToString("") { "%02X".format(it) }

        val decoded = QQMusicQrcLyricsParser.decryptHex(encrypted)

        assertTrue(decoded.contains("你(0,500)好(500,500)"))
    }

    @Test
    fun parsesAndAlignsTranslationAndRomanization() {
        val document = QQMusicQrcLyricsParser.parse(
            primary = "[1000,1000]你(1000,400)好(1400,600)",
            translation = "[00:01.00]Hello",
            romanization = "[00:01.00]ni hao",
        )

        assertEquals("你好", document.lines.single().text)
        assertEquals("Hello", document.lines.single().translation)
        assertEquals("ni hao", document.lines.single().romanization)
        assertEquals(2, document.lines.single().syllables.size)
    }

    @Test
    fun parsesBackgroundVocalsAndDuetAlignment() {
        val document = QQMusicQrcLyricsParser.parse(
            primary = "[1000,1200]甲(1000,500)唱(1500,500)\n[bg:1300,600]和(1300,300)声(1600,300)\n[2400,1000]：乙(2400,400)唱(2800,400)",
        )

        assertEquals(2, document.lines.size)
        assertEquals("和声", document.lines.first().accompaniment.single().text)
        assertEquals(LyricAgentAlignment.Normal, document.lines.first().agent?.alignment)
        assertEquals(LyricAgentAlignment.Flipped, document.lines.last().agent?.alignment)
    }

    private fun des(input: ByteArray, key: String, mode: Int): ByteArray =
        Cipher.getInstance("DES/ECB/NoPadding").run {
            init(mode, SecretKeySpec(key.toByteArray(), "DES"))
            doFinal(input)
        }
}
