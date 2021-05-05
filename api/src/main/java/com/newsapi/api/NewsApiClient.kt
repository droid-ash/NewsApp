package com.newsapi.api

import com.newsapi.api.service.NewsApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiClient {

    private val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.header(HEADER_X_API_KEY, BuildConfig.API_KEY)
        chain.proceed(requestBuilder.build())
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val client: NewsApi = retrofit.create(NewsApi::class.java)
}