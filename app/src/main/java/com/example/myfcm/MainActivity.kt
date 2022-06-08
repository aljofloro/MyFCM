package com.example.myfcm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.myfcm.service.MyFirebaseMessagingService

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val intent = intent
    val message = intent.getStringExtra("ServiceFCM")
    if(!message.isNullOrEmpty()){
      AlertDialog.Builder(this)
        .setTitle("Notificación")
        .setMessage(message)
        .setPositiveButton("OK",{dialog,which->}).show()
    }
  }
}