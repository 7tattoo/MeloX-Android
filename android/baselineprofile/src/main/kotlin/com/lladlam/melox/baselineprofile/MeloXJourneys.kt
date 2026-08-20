package com.lladlam.melox.baselineprofile

import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

internal const val MeloXPackage = "com.lladlam.melox.android"

internal fun MacrobenchmarkScope.startMeloX(): UiDevice {
    pressHome()
    startActivityAndWait()
    device.wait(Until.hasObject(By.pkg(MeloXPackage).depth(0)), 5_000)
    listOf("跳过", "稍后", "我知道了").forEach { label ->
        device.findObject(By.textContains(label))?.click()
    }
    return device
}

internal fun UiDevice.openSettingsAndScroll() {
    (findObject(By.desc("设置")) ?: findObject(By.text("设置")))?.click()
    waitForIdle()
    findObject(By.scrollable(true))?.fling(Direction.DOWN)
    waitForIdle()
    findObject(By.scrollable(true))?.fling(Direction.UP)
    waitForIdle()
}

internal fun UiDevice.exercisePlayerIfAvailable() {
    val miniPlayer = findObject(By.descContains("正在播放")) ?: return
    miniPlayer.click()
    waitForIdle()
    findObject(By.textContains("歌词"))?.click()
    waitForIdle()
}
