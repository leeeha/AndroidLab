package com.tutorial.c35

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.auto)

        val data = resources.getStringArray(R.array.language)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            data
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val autoData = arrayOf("apply", "apple", "below", "coco")
        val autoAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            autoData
        )
        autoCompleteTextView.setAdapter(autoAdapter)
    }
}
