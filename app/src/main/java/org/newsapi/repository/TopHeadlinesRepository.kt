package org.newsapi.repository

import com.newsapi.api.NewsApiClient

object TopHeadlinesRepository {

    private val api = NewsApiClient().client

    suspend fun getTopHeadlines(country: String, category: String) =
        api.getTopHeadlinesByCountry(country, category)
}