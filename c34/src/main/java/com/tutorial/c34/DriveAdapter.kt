package com.tutorial.c34

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat

class DriveAdapter(cxt: Context, val resId: Int, val data: MutableList<DriveVO>)
    : ArrayAdapter<DriveVO>(cxt, resId){

    override fun getCount(): Int {
        return data.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            // custom_item.xml 레이아웃 인플레이트 시키기
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                    as LayoutInflater
            view = inflater.inflate(resId, null)

            // 뷰 홀더에서 레이아웃과 뷰 바인딩 시키기
            val holder = DriveHolder(view)
            view!!.tag = holder
        }

        // 뷰 홀더에서 뷰 꺼내기
        val holder = view.tag as DriveHolder
        val typeImageView = holder.typeImageView
        val titleView = holder.titleView
        val dateView = holder.dateView
        val menuImageView = holder.menuImageView

        // 각 항목에 있는 뷰들의 속성 변경
        val (type, title, date) = data[position]
        titleView.text = title
        dateView.text = date

        // 각 항목의 타입에 따라 이미지 변경
        when (type) {
            "doc" -> {
                typeImageView.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        context.resources,
                        R.drawable.ic_type_doc, null
                    )
                )
            }
            "file" -> {
                typeImageView.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        context.resources,
                        R.drawable.ic_type_file, null
                    )
                )
            }
            "img" -> {
                typeImageView.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        context.resources,
                        R.drawable.ic_type_image, null
                    )
                )
            }
        }

        // 메뉴 이미지 클릭하면 토스트 메시지 뜨도록
        menuImageView.setOnClickListener {
            Toast.makeText(context, "$title menu click", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}