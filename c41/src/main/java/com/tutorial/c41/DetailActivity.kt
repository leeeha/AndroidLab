package com.tutorial.c41

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getStringExtra("id")
        val button = findViewById<Button>(R.id.finishButton)
        button.setOnClickListener {
            intent.putExtra("result", "hello $id")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}