package com.irv205.copilotcodingchallenge.domain.usecase

import com.irv205.copilotcodingchallenge.domain.model.Article
import com.irv205.copilotcodingchallenge.domain.repository.NewsRepository
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val repo: NewsRepository
) {
    suspend operator fun invoke(country: String = "us", pageSize: Int = 20): List<Article> {
        return repo.getTopHeadlinesTech(country, pageSize)
    }
}