package com.tutorial.c91

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tutorial.c91.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val oneFragment = OneFragment()
        val twoFragment = TwoFragment()

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.add(R.id.fragment_container, oneFragment)
        transaction.commit() // 이때 화면에 프래그먼트가 출력되면서, 트랜잭션 객체는 닫히게 됨.

        binding.oneButton.setOnClickListener {
            val tran = manager.beginTransaction() // 따라서, 다시 얻어줘야 함.
            tran.replace(R.id.fragment_container, oneFragment)
            tran.commit()
        }

        binding.twoButton.setOnClickListener {
            val tran = manager.beginTransaction()
            tran.replace(R.id.fragment_container, twoFragment)
            tran.commit()
        }
    }
}