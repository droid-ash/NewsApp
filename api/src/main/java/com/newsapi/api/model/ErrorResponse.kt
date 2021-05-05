package com.newsapi.api.model

data class ErrorResponse(
    val code: String,
    val message: String,
    val status: String
)