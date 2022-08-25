package com.vishishta.algovedixnew

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

val notificationid = 1
val channelID = "channel1"
val titleExtra = "titleExtra"
val messageExtra = "messageExtra"

class Notification_activiyt: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent)
    {
        val notification = NotificationCompat.Builder(context,channelID)
            .setSmallIcon(R.drawable.logo_app)
            .setContentTitle(intent.getStringExtra(titleExtra))
            .setContentText(intent.getStringExtra(messageExtra))
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationid, notification)
    }

}