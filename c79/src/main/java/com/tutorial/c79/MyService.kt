package com.tutorial.c79

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

class MyService : JobService() {

    override fun onCreate() {
        super.onCreate()
        Log.d("haeun", "MyService... onCreate...")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("haeun", "MyService... Destroy...")
    }

    override fun onStartJob(p0: JobParameters?): Boolean {
        Log.d("haeun", "MyService... onStartJob...")
        return false
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.d("haeun", "MyService... onStartJob...")
        return false
    }
}