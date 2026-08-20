package com.lladlam.melox

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.lladlam.melox.core.network.MeloXHttpClient

class MeloXApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MeloXHttpClient.initialize(this)
        registerActivityLifecycleCallbacks(MeloXAppVisibility)
    }
}

object MeloXAppVisibility : Application.ActivityLifecycleCallbacks {
    @Volatile
    private var startedActivities = 0

    val isForeground: Boolean get() = startedActivities > 0

    override fun onActivityStarted(activity: Activity) { startedActivities++ }
    override fun onActivityStopped(activity: Activity) { startedActivities = (startedActivities - 1).coerceAtLeast(0) }
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) = Unit
    override fun onActivityResumed(activity: Activity) = Unit
    override fun onActivityPaused(activity: Activity) = Unit
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit
    override fun onActivityDestroyed(activity: Activity) = Unit
}
