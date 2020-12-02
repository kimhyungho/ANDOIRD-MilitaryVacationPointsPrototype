package com.appdev.mvp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class PushMessenger : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("FCM_TEST", p0)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val title = remoteMessage.data["title"] //firebase에서 보낸 메세지의 title
        val message = remoteMessage.data["message"] //firebase에서 보낸 메세지의 내용
        val test = remoteMessage.data["test"]

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("test", test)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = "채널"
            val channel_nm = "채널명"

            val notichannel = getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
            val channelMessage = NotificationChannel(
                channel, channel_nm,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channelMessage.description = "채널에 대한 설명."
            channelMessage.enableLights(true)
            channelMessage.enableVibration(true)
            channelMessage.setShowBadge(false)
            channelMessage.vibrationPattern = longArrayOf(1000, 1000)
            notichannel.createNotificationChannel(channelMessage)

            // 푸시알림을 Builder를 이용하여 만듭니다.
            val notificationBuilder = NotificationCompat.Builder(this, channel)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setChannelId(channel)
                .setAutoCancel(true) // 선택시 자동 삭제
                .setContentIntent(pendingIntent) //알림 눌렀을 때 실행할 인텐트
                .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE);

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(9999, notificationBuilder.build());
        } else {
            val notificationBuilder = NotificationCompat.Builder(this, "")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(9999, notificationBuilder.build())
        }
    }
}