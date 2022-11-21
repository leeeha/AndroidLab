package com.tutorial.c79

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val scheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            JobInfo.Builder(1, ComponentName(this, MyService::class.java)).run {
                setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED) // 와이파이
                scheduler.schedule(build())
            }
        }
    }
}