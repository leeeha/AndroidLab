package com.tutorial.c51

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ){
            if(it){
                Toast.makeText(this, "granted...", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "denied...", Toast.LENGTH_SHORT).show()
            }
        }

        val status = ContextCompat.checkSelfPermission(
            this,
            "android.permission.ACCESS_FINE_LOCATION"
        )

        if(status == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "granted...", Toast.LENGTH_SHORT).show()
        }else{
            // 요청 다이얼로그 띄우기
            // test1...
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf("android.permission.ACCESS_FINE_LOCATION"),
//                100
//            )

            // test2...
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    // 요청 다이얼로그 응답 결과에 대한 처리
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "granted...", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "denied...", Toast.LENGTH_SHORT).show()
        }
    }
}
