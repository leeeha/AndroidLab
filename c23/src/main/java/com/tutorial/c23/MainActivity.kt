package com.tutorial.c23

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var initTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // 3초 이내에 백 버튼이 2번 눌리면 앱 종료하기
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            // 3초보다 더 걸리면 토스트 메시지 띄우기
            if(System.currentTimeMillis() - initTime > 3000){
                Toast.makeText(this, "종료하려면 한번 더 누르세요!",
                    Toast.LENGTH_SHORT).show()
                initTime = System.currentTimeMillis() // 시간 업데이트
                return true // 키 이벤트에 대한 처리 무시
            }
        }

        // 3초보다 적게 걸리면 백 버튼 기능이 적용되어 앱 종료됨.
        return super.onKeyDown(keyCode, event)
    }
}
