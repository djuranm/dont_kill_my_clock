package com.djuranm.dontkillmyclock.boot

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.djuranm.dontkillmyclock.persistance.PersistenceCenter
import com.djuranm.dontkillmyclock.service.ClockForegroundService

class BootUpBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val ctx = context ?: return
        if (PersistenceCenter.getState(ctx.applicationContext)) {
            ContextCompat.startForegroundService(ctx, ClockForegroundService.getIntent(ctx))
        }
    }

}