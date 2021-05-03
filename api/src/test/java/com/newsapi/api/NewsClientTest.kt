package com.newsapi.api

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class NewsClientTest {

    private val newsApiClient = NewsApiClient()

    companion object {
        const val TEST_COUNTRY_IN = "in"
        const val TEST_OK = "ok"
        const val TEST_CATEGORY_GENERAL = "general"
    }

    @Test
    fun testGetTopHeadlines() {
        runBlocking {
            val topHeadLines = newsApiClient.client.getTopHeadlinesByCountry(
                TEST_COUNTRY_IN,
                TEST_CATEGORY_GENERAL
            )
            val body = topHeadLines.body()
            println("Total results: ${body?.totalResults}")
            assertEquals(body?.status, TEST_OK)
        }
    }

    @Test
    fun testSearchHeadlines() {
        runBlocking {
            val topHeadLines = newsApiClient.client.searchHeadlines("elon musk")
            val body = topHeadLines.body()
            println("Total results: ${body?.totalResults}")
            assertEquals(body?.status, TEST_OK)
        }
    }
}