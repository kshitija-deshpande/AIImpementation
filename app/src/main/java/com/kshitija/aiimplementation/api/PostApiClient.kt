package com.kshitija.aiimplementation.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object PostApiClient {
    val postApiService: PostApiService by lazy {
        RetrofitClient.retrofit.create(PostApiService::class.java)
    }
}