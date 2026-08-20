package com.lladlam.melox

import android.app.Application
import com.lladlam.melox.core.network.MeloXHttpClient

class MeloXApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MeloXHttpClient.initialize(this)
    }
}
