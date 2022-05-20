package com.example.github.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClientManager private constructor() {

    private val retrofit: Retrofit
    private val okhttp = OkHttpClient()

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(Config.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttp)
            .build()
    }

    companion object{
        private val manager= ApiClientManager()
        val retrofit: Retrofit
            get() = manager.retrofit
    }



}