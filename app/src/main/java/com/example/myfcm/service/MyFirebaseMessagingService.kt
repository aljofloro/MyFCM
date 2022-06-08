package com.example.myfcm.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.myfcm.MainActivity
import com.example.myfcm.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
  val TAG = "ServiceFCM"
  val REQUEST = 0

  override fun onMessageReceived(message: RemoteMessage) {
    super.onMessageReceived(message)
    Log.d(TAG,"Desde: "+message!!.from)
    Log.d(TAG,"Cuerpo de la Notificación: "+ message.notification!!.body)
    sendNotification(message)

    val intent = Intent(this@MyFirebaseMessagingService, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intent.putExtra(TAG,message.notification!!.body)
    startActivity(intent)
  }

  private fun sendNotification(message: RemoteMessage){
    val intent = Intent(this,MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    val pendingIntent = PendingIntent.getActivity(this,REQUEST,
    intent,PendingIntent.FLAG_ONE_SHOT)

    val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

    val notificationBuilder = NotificationCompat.Builder(this)
      .setContentText(message.notification!!.body)
      .setAutoCancel(true)
      .setSmallIcon(R.mipmap.ic_launcher_round)
      .setSound(defaultSoundUri)
      .setContentIntent(pendingIntent)

    val notificationManager
    = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    notificationManager.notify(TAG,0,notificationBuilder.build())
  }

}