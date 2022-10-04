package com.tutorial.c47

import android.os.AsyncTask
import android.os.Bundle
import android.os.SystemClock
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var startView: ImageView
    lateinit var pauseView: ImageView
    lateinit var textView: TextView

    var isFirst = true

    lateinit var asyncTask: MyAsyncTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startView = findViewById(R.id.main_startBtn)
        pauseView = findViewById(R.id.main_pauseBtn)
        textView = findViewById(R.id.main_textView)

        startView.setOnClickListener {
            if(isFirst){
                asyncTask.isRun = true
                asyncTask.execute() // 스레드 실행
                isFirst = false
            }else{
                asyncTask.isRun = true
            }
        }

        pauseView.setOnClickListener {
            asyncTask.isRun = false
        }

        asyncTask = MyAsyncTask() // 객체 생성
    }

    inner class MyAsyncTask: AsyncTask<Void?, Int?, String>(){
        var loopFlag = true
        var isRun = false

        // 스레드에 의해 백그라운드에서 처리될 내용을 담는다.
        override fun doInBackground(vararg p0: Void?): String {
            var count = 10
            while(loopFlag){
                SystemClock.sleep(1000)
                if(isRun){
                    count--
                    publishProgress(count) // onProgressUpdate
                    if(count == 0){
                        loopFlag = false
                    }
                }
            }
            return "Finish!!" // onPostExecute
        }

        // doInBackground에서 publishProgress 함수로 넘긴 값이 전달됨.
        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            textView.text = values[0].toString() // count 값
        }

        // doInBackground 함수의 최종 결과 값을 받기 위해 사용함.
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            textView.text = result // Finish 문자열
        }
    }
}
