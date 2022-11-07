package com.tutorial.c64

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.image_view)
        val button = findViewById<Button>(R.id.button)

        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            try {
                val option = BitmapFactory.Options()
                option.inSampleSize = 5

                // 컨텐츠 프로바이더에게 제공받은 식별자 값으로 비트맵 객체 생성
                val inputStream = contentResolver.openInputStream(it.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()

                bitmap?.let {
                    imageView.setImageBitmap(bitmap) // 이미지 설정
                } ?: let {
                    Toast.makeText(this, "이미지 로딩 실패", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            launcher.launch(intent) // 암시적 인텐트로 갤러리 앱 띄우기
        }
    }
}