package com.lladlam.melox.core.lyrics

import org.junit.Assert.assertTrue
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

    private fun des(input: ByteArray, key: String, mode: Int): ByteArray =
        Cipher.getInstance("DES/ECB/NoPadding").run {
            init(mode, SecretKeySpec(key.toByteArray(), "DES"))
            doFinal(input)
        }
}
