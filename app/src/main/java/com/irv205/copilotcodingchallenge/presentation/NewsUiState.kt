package com.irv205.copilotcodingchallenge.presentation

import com.irv205.copilotcodingchallenge.domain.model.Article

sealed interface NewsUiState {
    data object Loading : NewsUiState
    data class Success(val articles: List<Article>) : NewsUiState
    data class Error(val message: String) : NewsUiState
}