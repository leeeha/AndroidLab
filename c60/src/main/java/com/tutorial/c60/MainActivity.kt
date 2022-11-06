package com.tutorial.c60

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titleView = findViewById<EditText>(R.id.add_title)
        val contentView = findViewById<EditText>(R.id.add_content)
        val addBtn = findViewById<Button>(R.id.add_btn)

        addBtn.setOnClickListener {
            val title = titleView.text.toString()
            val content = contentView.text.toString()
            val db = DBHelper(this).writableDatabase

//            db.execSQL("insert into tb_memo (title, content) values (?, ?)",
//                arrayOf(title, content))
            val values = ContentValues()
            values.put("title", title)
            values.put("content", content)
            db.insert("tb_memo", null, values)
            db.close()

            val intent = Intent(this, ReadActivity::class.java)
            startActivity(intent)
        }
    }
}