package com.tutorial.c23

import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var resultView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultView = findViewById(R.id.resultView)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var eventType = ""

        when(event?.action){
            MotionEvent.ACTION_DOWN -> eventType = "DOWN EVENT"
            MotionEvent.ACTION_UP -> eventType = "UP EVENT"
            MotionEvent.ACTION_MOVE -> eventType = "MOVE EVENT"
        }

        resultView.text = "$eventType : (${event?.x},  ${event?.y})"

        return super.onTouchEvent(event)
    }
}
