package com.djuranm.dontkillmyclock.boot

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.djuranm.dontkillmyclock.notification.NotificationCenter
import com.djuranm.dontkillmyclock.persistance.PersistenceCenter

class BootUpBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val ctx = context ?: return
        NotificationCenter.updateNotification(
            ctx.applicationContext,
            PersistenceCenter.getState(ctx.applicationContext)
        )
    }

}