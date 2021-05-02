package org.newsapi.api

import okhttp3.OkHttpClient
import org.newsapi.api.service.NewsApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NewsApiClient {

    val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.header("X-Api-Key", "7ebb4cde0ffa4410b846668c9ef1a140")
        chain.proceed(requestBuilder.build())
    }.build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    val api = retrofit.create(NewsApi::class.java)
}