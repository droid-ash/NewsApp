package org.newsapi.api

import junit.framework.TestCase.assertEquals
import org.junit.Test

class NewsClientTest {

    private val newsApiClient = NewsApiClient()

    @Test
    fun testGetTopHeadLines() {
        val topHeadLines = newsApiClient.api.getTopHeadLines("in").execute()
        val body = topHeadLines.body()
        println("Total results: ${body?.totalResults}")
        assertEquals(body?.status, "ok")
    }
}