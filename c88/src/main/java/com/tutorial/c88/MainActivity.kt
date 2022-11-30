package com.tutorial.c88

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultView = findViewById<ImageView>(R.id.resultView)

        Glide.with(this)
            .load("https://www.edigitalagency.com.au/uploads/google-logo-png-transparent-background-large-new.png")
            .override(200, 200)
            .placeholder(R.drawable.loading)
            .error(R.drawable.error)
            .into(resultView)
    }
}