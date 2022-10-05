package com.tutorial.c49

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    val backgroundScope = CoroutineScope(Dispatchers.Default + Job())

    lateinit var button: Button
    lateinit var resultView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        resultView = findViewById(R.id.resultView)

        // 버튼 클릭하고 나서도 다른 뷰를 건드릴 수 있음.
        button.setOnClickListener {
            backgroundScope.launch {
                var sum = 0L
                // 시간이 오래 걸리는 작업
                var time = measureTimeMillis {
                    for(i in 1..2_000_000_000){
                        sum += i
                    }
                }
                // UI 작업은 메인 디스패처가 처리
                withContext(Dispatchers.Main){
                    resultView.text = "sum: $sum"
                }
            }
        }
    }
}