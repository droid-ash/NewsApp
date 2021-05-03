package org.newsapi.ui.top

import org.newsapi.api.NewsApiClient

object TopHeadlinesRepository {

    private val api = NewsApiClient().client

    suspend fun getTopHeadlines(country: String, category: String) =
        api.getTopHeadlinesByCountry(country, category)
}