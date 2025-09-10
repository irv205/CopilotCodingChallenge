package com.irv205.copilotcodingchallenge.domain.repository

import com.irv205.copilotcodingchallenge.domain.model.Article

interface NewsRepository {
    suspend fun getTopHeadlinesTech(country: String = "us", pageSize: Int = 20): List<Article>
}