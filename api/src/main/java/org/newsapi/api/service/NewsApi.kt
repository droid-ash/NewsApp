package org.newsapi.api.service

import org.newsapi.api.model.NewsApiResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    fun getTopHeadLines(@Query("country") country: String): Call<NewsApiResponse>
}