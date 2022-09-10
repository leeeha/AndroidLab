package com.tutorial.c32

import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.main_list)
        val data: ArrayList<HashMap<String, String>> = ArrayList()
        var map: HashMap<String, String> = HashMap()
        map["name"] = "LG 트윈스"
        map["content"] = "서울, 잠실 야구장"
        data.add(map)

        map = HashMap()
        map["name"] = "두산 베어스"
        map["content"] = "서울, 잠실 야구장"
        data.add(map)

        map = HashMap()
        map["name"] = "KT 위즈"
        map["content"] = "수원, KT 위즈 파크"
        data.add(map)

        val adapter = SimpleAdapter(
            this,
            data,
            android.R.layout.simple_list_item_2,
            arrayOf("name", "content"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        listView.adapter = adapter
    }
}