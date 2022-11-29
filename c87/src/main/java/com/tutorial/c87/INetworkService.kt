package com.tutorial.c87

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface INetworkService {
    @GET("api/users")
    fun getUserList(@Query("page") page: String): Call<UserListModel>
}