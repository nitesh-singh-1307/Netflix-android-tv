package com.example.netflix.networks

import com.example.netflix.networks.RetrofitInstance.retrofit
import com.example.netflix.ui.screen.discover.DiscoverDataApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
            // Log request and response bodies
        })
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val requestWithHeaders = originalRequest.newBuilder()
                .addHeader("x-rapidapi-key", "78d91e9c7emshc1d143c11a9fdd7p101fa3jsn3e8c325f91c6")
                .addHeader("x-rapidapi-host", "tvview.p.rapidapi.com")
                .build()
            chain.proceed(requestWithHeaders)
        }.build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://tvview.p.rapidapi.com")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create()) // Or your preferred converter
        .build()
}

val movieApi = retrofit.create(DiscoverDataApi::class.java)