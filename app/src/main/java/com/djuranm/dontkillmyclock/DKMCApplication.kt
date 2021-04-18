package com.djuranm.dontkillmyclock

import android.app.Application
import com.djuranm.dontkillmyclock.notification.NotificationCenter

class DKMCApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        NotificationCenter.createNotificationChannel(this)
    }

}