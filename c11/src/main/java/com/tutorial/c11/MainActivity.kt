package com.tutorial.c11

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = TextView(this).apply {
            typeface = Typeface.DEFAULT_BOLD
            text = "SeSAC"
        }

        val image = ImageView(this).also {
            it.setImageDrawable(ContextCompat.getDrawable(
                    this, R.drawable.logo_1
            ))
        }

        val title = TextView(this).apply {
            typeface = Typeface.DEFAULT_BOLD
            text = "Jetpack과 코틀린을 활용한 안드로이드 앱 개발"
        }

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL // 방향
            gravity = Gravity.CENTER // 정렬
            addView(name, WRAP_CONTENT, WRAP_CONTENT)
            addView(image, WRAP_CONTENT, WRAP_CONTENT)
            addView(title, WRAP_CONTENT, WRAP_CONTENT)
        }

        setContentView(layout)
    }
}
