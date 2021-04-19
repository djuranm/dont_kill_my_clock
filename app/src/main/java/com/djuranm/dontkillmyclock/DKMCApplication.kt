package com.djuranm.dontkillmyclock

import android.app.Application
import com.djuranm.dontkillmyclock.service.ClockForegroundService

class DKMCApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ClockForegroundService.createNotificationChannel(this)
    }

}