package com.irv205.copilotcodingchallenge.data.mapper

import com.irv205.copilotcodingchallenge.data.remote.dto.ArticleDto
import com.irv205.copilotcodingchallenge.domain.model.Article


fun ArticleDto.toDomain(): Article =
    Article(
        title = title.orEmpty(),
        description = description.orEmpty(),
        imageUrl = urlToImage.orEmpty(),
        publishedAt = publishedAt.orEmpty(),
        sourceName = source?.name.orEmpty(),
        url = url.orEmpty()
    )