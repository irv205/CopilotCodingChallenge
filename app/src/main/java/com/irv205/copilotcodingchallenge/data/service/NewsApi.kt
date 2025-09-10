package com.irv205.copilotcodingchallenge.data.service

import com.irv205.copilotcodingchallenge.data.remote.dto.NewsApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlinesTech(
        @Query("category") category: String = "technology",
        @Query("country") country: String = "us",
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String
    ): NewsApiResponse
}