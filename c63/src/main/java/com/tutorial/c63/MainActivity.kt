package com.tutorial.c63

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val resultView = findViewById<TextView>(R.id.resultView)

        // 다른 액티비티로부터 결과 값을 받는 경우 -> StartActivityForResult
        val requestActivity = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            val cursor = contentResolver.query(
                it.data!!.data!!, // 유저가 선택한 항목의 식별자 값 (uri)
                arrayOf(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, // 이름
                    ContactsContract.CommonDataKinds.Phone.NUMBER // 전화번호
                ),
                null,
                null,
                null
            )

            var name = "none"
            var phone = "none"
            if (cursor!!.moveToFirst()) {
                name = cursor.getString(0)
                phone = cursor.getString(1)
            }

            // 이름과 전화번호를 텍스트뷰에 보여주기
            resultView.text = "result: name - ${name}, phone - ${phone}"
        }

        // 주소록에 대한 퍼미션 요청
        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) { // 퍼미션이 허용되면
                // 주소록 목록 화면으로 넘어가기
                val intent = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                requestActivity.launch(intent)
            } else {
                Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show()
            }
        }

        button.setOnClickListener {
            // 주소록 퍼미션 허용 여부 확인
            val status = ContextCompat.checkSelfPermission(this,
                "android.permission.READ_CONTACTS")
            if(status == PackageManager.PERMISSION_GRANTED){
                // 주소록 목록 화면으로 넘어가기
                val intent = Intent(Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
                requestActivity.launch(intent)
            }else{
                permissionLauncher.launch("android.permission.READ_CONTACTS")
            }
        }
    }
}

