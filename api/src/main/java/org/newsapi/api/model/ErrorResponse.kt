package org.newsapi.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "code")
    val code: String,
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: String
)