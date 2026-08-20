package com.lladlam.melox.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.withFrameNanos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import java.io.File

private data class PerfSnapshot(
    val fps: Float = 0f,
    val avgMs: Float = 0f,
    val maxMs: Float = 0f,
    val totalFrames: Int = 0,
)

/**
 * Lightweight on-screen FPS/frame-time overlay + file logger for transition
 * profiling. Only enabled in debug builds from MeloXApp.
 *
 * Logs are written to the app's external files dir so they can be pulled with:
 *   adb pull /sdcard/Android/data/com.lladlam.melox.android/files/melox_perf.log
 */
@Composable
fun MeloXPerformanceOverlay(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var snapshot by remember { mutableStateOf(PerfSnapshot()) }

    LaunchedEffect(Unit) {
        val frameTimesNs = LongArray(90)
        var idx = 0
        var lastFrameNs = withFrameNanos { it }
        while (isActive) {
            withFrameNanos { frameNs ->
                val delta = frameNs - lastFrameNs
                lastFrameNs = frameNs
                if (delta > 0) {
                    frameTimesNs[idx % frameTimesNs.size] = delta
                    idx++
                    if (idx % 6 == 0) {
                        val count = minOf(idx, frameTimesNs.size)
                        var sumNs = 0L
                        var maxNs = 0L
                        repeat(count) { sampleIndex ->
                            val sample = frameTimesNs[sampleIndex]
                            sumNs += sample
                            if (sample > maxNs) maxNs = sample
                        }
                        val avgNs = if (count > 0) sumNs.toDouble() / count else 0.0
                        val fps = if (avgNs > 0) (1_000_000_000.0 / avgNs).toFloat() else 0f
                        snapshot = PerfSnapshot(
                            fps = fps,
                            avgMs = (avgNs / 1_000_000.0).toFloat(),
                            maxMs = (maxNs / 1_000_000.0).toFloat(),
                            totalFrames = idx,
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        val logFile = File(context.getExternalFilesDir(null), "melox_perf.log").apply {
            parentFile?.mkdirs()
            if (exists()) delete()
        }
        val writer = logFile.bufferedWriter()
        try {
            writer.appendLine("# ts,fps,avg_ms,max_ms,frames")
            writer.flush()
            while (isActive) {
                delay(5_000L)
                val s = snapshot
                val line = buildString {
                    append(System.currentTimeMillis())
                    append(",")
                    append("%.1f".format(s.fps))
                    append(",")
                    append("%.2f".format(s.avgMs))
                    append(",")
                    append("%.2f".format(s.maxMs))
                    append(",")
                    append(s.totalFrames)
                }
                withContext(Dispatchers.IO) {
                    writer.appendLine(line)
                }
            }
        } finally {
            withContext(Dispatchers.IO) {
                writer.close()
            }
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopEnd,
    ) {
        Text(
            text = buildString {
                appendLine("FPS ${snapshot.fps.toInt()}")
                appendLine("avg ${snapshot.avgMs.toInt()}ms")
                appendLine("max ${snapshot.maxMs.toInt()}ms")
                append("frm ${snapshot.totalFrames}")
            },
            modifier = Modifier
                .padding(top = 48.dp, end = 12.dp)
                .background(Color.Black.copy(alpha = 0.72f), RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp),
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
