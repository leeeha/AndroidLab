package com.tutorial.c41

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var resultView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        resultView = findViewById(R.id.resultView)

        btn1.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", "first")
            startActivityForResult(intent, 10)
        }

        val resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            resultView.text = "result: ${it.data?.getStringExtra("result")}"
        }

        btn2.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", "second")
            resultLauncher.launch(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 10 && resultCode == RESULT_OK){
            val result: String? = data?.getStringExtra("result")
            resultView.text = "result : $result"
        }
    }
}
