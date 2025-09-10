package com.irv205.copilotcodingchallenge.domain.model


data class Article(
    val title: String,
    val description: String,
    val imageUrl: String,
    val publishedAt: String,
    val sourceName: String,
    val url: String
)