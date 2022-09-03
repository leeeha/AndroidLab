package com.tutorial.c23

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var pauseTime = 0L
    lateinit var startButton: Button
    lateinit var stopButton: Button
    lateinit var resetButton: Button
    lateinit var chronometer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)
        resetButton = findViewById(R.id.resetButton)
        chronometer = findViewById(R.id.chronometer)

        startButton.setOnClickListener {
            // elapsedRealtime(): 부팅 시점부터 현재까지 흘러간 시간을 ms로 반환
            chronometer.base = SystemClock.elapsedRealtime() + pauseTime
            chronometer.start()

            stopButton.isEnabled = true
            resetButton.isEnabled = true
            startButton.isEnabled = false
        }

        stopButton.setOnClickListener {
            pauseTime =  SystemClock.elapsedRealtime() - chronometer.base
            Log.e("PAUSE TIME", (pauseTime / 1000).toString())
            chronometer.stop()

            stopButton.isEnabled = false
            resetButton.isEnabled = true
            startButton.isEnabled = true
        }

        resetButton.setOnClickListener {
            pauseTime = 0L
            chronometer.base = SystemClock.elapsedRealtime()
            chronometer.stop()

            stopButton.isEnabled = false
            resetButton.isEnabled = false
            startButton.isEnabled = true
        }
    }
}
