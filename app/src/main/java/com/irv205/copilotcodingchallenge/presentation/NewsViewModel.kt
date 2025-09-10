package com.irv205.copilotcodingchallenge.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irv205.copilotcodingchallenge.domain.usecase.GetTopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getTopHeadlines: GetTopHeadlinesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
    val state: StateFlow<NewsUiState> = _state

    fun load() {
        if (_state.value is NewsUiState.Success) return
        _state.value = NewsUiState.Loading
        viewModelScope.launch {
            try {
                val data = getTopHeadlines(country = "us", pageSize = 20)
                _state.value = NewsUiState.Success(data)
            } catch (e: Throwable) {
                _state.value = NewsUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun retry() = load()
}