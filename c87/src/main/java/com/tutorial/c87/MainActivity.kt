package com.tutorial.c87

import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)
        val networkService = retrofit.create(INetworkService::class.java)
        val call = networkService.getUserList("1")

        call.enqueue(object: Callback<UserListModel> {
            override fun onResponse(call: Call<UserListModel>, response: Response<UserListModel>) {
                val userList = response.body()
                val mutableList = mutableListOf<Map<String, String>>()
                userList?.data?.forEach {
                    val map = mapOf("firstName" to it.firstName, "lastName" to it.lastName)
                    mutableList.add(map)
                }
                val adapter = SimpleAdapter(
                    this@MainActivity,
                    mutableList,
                    android.R.layout.simple_expandable_list_item_2,
                    arrayOf("firstName", "lastName"),
                    intArrayOf(android.R.id.text1, android.R.id.text2)
                )
                listView.adapter = adapter
            }

            override fun onFailure(call: Call<UserListModel>, t: Throwable) {
                call.cancel()
            }
        })
    }
}