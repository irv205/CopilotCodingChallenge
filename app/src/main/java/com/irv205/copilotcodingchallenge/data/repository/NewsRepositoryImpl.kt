package com.irv205.copilotcodingchallenge.data.repository

import com.irv205.copilotcodingchallenge.BuildConfig
import com.irv205.copilotcodingchallenge.data.mapper.toDomain
import com.irv205.copilotcodingchallenge.data.service.NewsApi
import com.irv205.copilotcodingchallenge.domain.model.Article
import com.irv205.copilotcodingchallenge.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository {
    override suspend fun getTopHeadlinesTech(country: String, pageSize: Int): List<Article> {
        val res = api.getTopHeadlinesTech(
            category = "technology",
            country = country,
            pageSize = pageSize,
            apiKey = BuildConfig.NEWS_API_KEY
        )
        return res.articles.orEmpty().map { it.toDomain() }
    }
}