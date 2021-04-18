package com.djuranm.dontkillmyclock.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.djuranm.dontkillmyclock.R
import java.text.SimpleDateFormat
import java.util.*

class NotificationCenter {

    companion object {

        private const val CHANNEL_ID: String = "Do_not_kill_my_clock"
        private const val NOTIFICATION_ID: Int = Int.MAX_VALUE

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

        fun updateNotification(context: Context, show: Boolean) {
            if (!show) {
                with(NotificationManagerCompat.from(context)) {
                    cancel(NOTIFICATION_ID)
                }
                return
            }

            val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(sdf.format(Date()))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setOngoing(true)
            with(NotificationManagerCompat.from(context)) {
                notify(NOTIFICATION_ID, builder.build())
            }
        }

    }

}