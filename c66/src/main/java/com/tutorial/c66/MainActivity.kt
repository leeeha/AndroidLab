package com.tutorial.c66

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val dataButton = findViewById<Button>(R.id.dataButton)
        val fileButton = findViewById<Button>(R.id.fileButton)

        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            // 부가 데이터로부터 비트맵 객체 획득
            val bitmap = it.data?.extras?.get("data") as Bitmap
            bitmap.let{
                imageView.setImageBitmap(bitmap)
            }
        }

        dataButton.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) // 이미지 캡쳐 액션
            launcher.launch(intent) // 암시적 인텐트로 카메라 앱 띄우기
        }

        var filePath = ""
        val fileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            // 이미지 파일로부터 비트맵 객체 생성
            val option = BitmapFactory.Options()
            option.inSampleSize = 3
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                imageView.setImageBitmap(bitmap)
            }
        }

        fileButton.setOnClickListener {
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )
            filePath = file.absolutePath // 파일 경로명 설정
            val uri = FileProvider.getUriForFile(
                this,
                "com.tutorial.c66.fileprovider",
                file
            )

            // 암시적 인텐트의 부가 데이터로 파일 uri 등록하고 카메라 앱 띄우기
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            fileLauncher.launch(intent)
        }
    }
}
