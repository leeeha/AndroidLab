package com.tutorial.c34

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mutableList = mutableListOf<DriveVO>()
        mutableList.add(DriveVO("doc", "안드로이드", "2월 6일"))
        mutableList.add(DriveVO("file", "db.zip", "2월 6일"))
        mutableList.add(DriveVO("img", "exam.png", "2월 6일"))

        val listView = findViewById<ListView>(R.id.custom_list)
        val adapter = DriveAdapter(this, R.layout.custom_item, mutableList)
        listView.adapter = adapter
    }
}
