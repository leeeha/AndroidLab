package com.tutorial.c47

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var startView: ImageView
    lateinit var pauseView: ImageView
    lateinit var textView: TextView

    var loopFlag = true
    var isFirst = true
    var isRun = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startView = findViewById(R.id.main_startBtn)
        pauseView = findViewById(R.id.main_pauseBtn)
        textView = findViewById(R.id.main_textView)

        startView.setOnClickListener {
            if(isFirst){
                isFirst = false
                isRun = true
                thread.start() // 개발자가 정의한 별도의 스레드 시작
            }else{
                isRun = true
            }
        }

        // 정지 버튼 누르면 count가 감소하지 않음.
        pauseView.setOnClickListener {
            isRun = false
        }
    }

    // 메인 스레드에서는 핸들러 객체의 handleMessage 함수 오버라이딩
    var handler: Handler = object: Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if(msg.what === 1){
                textView.text = msg.arg1.toString() // count 값 보여주기
            }else if(msg.what === 2){
                textView.text = msg.obj as String // Finish 문자열 보여주기
            }
        }
    }

    var thread: Thread = object: Thread(){
        override fun run() {
            try{
                var count = 10
                while(loopFlag){
                    sleep(1000) // 1초씩 숫자가 감소하도록
                    if(isRun){
                        count--

                        var message = Message() // 메시지 객체 생성
                        message.what = 1
                        message.arg1 = count
                        handler.sendMessage(message) // 핸들러에게 작업 의뢰

                        if(count == 0){
                            message = Message()
                            message.what = 2
                            message.obj = "Finish!!"
                            handler.sendMessage(message) // 핸들러에게 작업 의뢰
                            loopFlag = false // 반복문 종료
                        }
                    }
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
