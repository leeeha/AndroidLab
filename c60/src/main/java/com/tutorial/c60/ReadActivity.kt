package com.tutorial.c60

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val titleView = findViewById<TextView>(R.id.read_title)
        val contentView = findViewById<TextView>(R.id.read_content)

        val db = DBHelper(this).readableDatabase

        // 최신 입력 데이터 1건을 얻어서 텍스트뷰에 출력하기
//        val cursor = db.rawQuery("select title, content from tb_memo " +
//                "order by _id desc limit 1", null)

        val cursor = db.query("tb_memo", arrayOf("title, content"),
            null, null, null, null,
            "_id desc limit 1")

        while(cursor.moveToNext()){
            titleView.text = cursor.getString(0)
            contentView.text = cursor.getString(1)
        }
        db.close()
    }
}
