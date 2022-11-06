package com.tutorial.c63

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

val PERMISSIONS_REQUEST_CODE = 100
var REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.READ_CONTACTS)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val resultView = findViewById<TextView>(R.id.resultView)

        val requestActivity: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            // 주소록에서 메인 액티비티로 다시 돌아오면
            val cursor = contentResolver.query(
                it.data!!.data!!, // 유저가 선택한 항목의 식별자 값
                arrayOf(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, // 이름
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                ), // 전화번호
                null,
                null,
                null
            )

            var name = "none"
            var phone = "none"
            if (cursor!!.moveToFirst()) {
                name = cursor?.getString(0)
                phone = cursor?.getString(1)
            }

            resultView.text = "result: name - ${name}, phone - ${phone}"
        }

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
            } else { // todo: 퍼미션 재요청
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
            }else{ // todo: 퍼미션 재요청
                permissionLauncher.launch("android.permission.READ_CONTACTS")
            }
        }
    }
}

