package com.newsapi.api.model

import com.newsapi.api.getTimestamp
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.UUID

@JsonClass(generateAdapter = true)
data class Article(
    @Transient
    val articleUniqueId: String = UUID.randomUUID().toString(),
    @Json(name = "author")
    val author: String?,
    @Json(name = "content")
    val content: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "publishedAt")
    val publishedAt: String,
    @Transient
    val modifiedPublishedAt: String = getTimestamp(publishedAt),
    @Json(name = "source")
    val source: Source,
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "urlToImage")
    val urlToImage: String?
)