package com.example.github.api

import com.example.github.model.data.Data
import com.example.github.model.data.DetailUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    @Headers("Authorization: token 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")
    fun getPost(): Call<MutableList<Data>>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUser>


}