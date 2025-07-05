package com.example.authapp.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log


object RetrofitClient {

    private val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor (logging)
        .build()

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://my-api-login.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}