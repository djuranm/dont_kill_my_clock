package com.djuranm.dontkillmyclock.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.djuranm.dontkillmyclock.R
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ClockForegroundService : Service() {

    companion object {
        private const val CHANNEL_ID: String = "Do_not_kill_my_clock"
        private const val NOTIFICATION_ID: Int = Int.MAX_VALUE
        private const val UPDATE_CYCLE: Long = 60 * 1000 // 1 minute

        fun getIntent(context: Context): Intent {
            return Intent(context, ClockForegroundService::class.java)
        }

        fun createNotificationChannel(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = context.getString(R.string.notification_channel_name)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(CHANNEL_ID, name, importance)
                val notificationManager: NotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }

    }

    private val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val scope = MainScope()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        startForeground(NOTIFICATION_ID, getNotification(sdf.format(Date())))

        scope.launch {
            while (true) {
                delay(UPDATE_CYCLE)
                with(NotificationManagerCompat.from(this@ClockForegroundService)) {
                    notify(NOTIFICATION_ID, getNotification(sdf.format(Date())))
                }
            }
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }

    private fun getNotification(time: String) = NotificationCompat.Builder(this, CHANNEL_ID)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle(time)
        .build()

}