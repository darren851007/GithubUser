package com.example.github.api

import com.example.github.model.Data
import com.example.github.model.DetailUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.lang.annotation.Inherited
import retrofit2.http.Header as Header1

interface ApiService {
    @GET("/users")
    fun getPost(): Call<MutableList<Data>>

    @GET("/users/{username}")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<MutableList<DetailUser>>


}