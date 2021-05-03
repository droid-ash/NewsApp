package org.newsapi.api

import okhttp3.OkHttpClient
import org.newsapi.api.service.NewsApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NewsApiClient {

    private val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.header(HEADER_X_API_KEY, API_KEY)
        chain.proceed(requestBuilder.build())
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

    val client: NewsApi = retrofit.create(NewsApi::class.java)
}