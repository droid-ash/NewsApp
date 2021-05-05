package com.newsapi.api.service

import com.newsapi.api.*
import com.newsapi.api.model.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET(TOP_HEADLINES)
    suspend fun getTopHeadlinesByCountry(
        @Query(QUERY_COUNTRY) country: String,
        @Query(QUERY_CATEGORY) category: String?
    ): Response<NewsApiResponse>

    @GET(EVERYTHING)
    suspend fun searchHeadlines(
        @Query(QUERY_Q) query: String?,
        @Query(QUERY_SORT_BY) sortBy: String = SORT_BY_POPULARITY,
        @Query(QUERY_PAGE_SIZE) pageSize: Int = MAX_PAGE_SIZE
    ): Response<NewsApiResponse>
}