package com.tutorial.c71

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<ImageView>(R.id.startButton)
        val stopButton = findViewById<ImageView>(R.id.stopButton)

        startButton.setOnClickListener {
            val intent = Intent("PLAY_TO_SERVICE")
            intent.putExtra("mode", "start")
            sendBroadcast(intent)

            startButton.isEnabled = false
            stopButton.isEnabled = true
        }

        stopButton.setOnClickListener {
            val intent = Intent("PLAY_TO_SERVICE")
            intent.putExtra("mode", "stop")
            sendBroadcast(intent)

            startButton.isEnabled = true
            stopButton.isEnabled = false
        }

        val intent = Intent(this, MyService::class.java)
        startService(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        val intent = Intent(this, MyService::class.java)
        stopService(intent)
    }
}