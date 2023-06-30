package com.greenbot.cinema.android

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greenbot.cinema.MoviesRepository
import com.greenbot.cinema.entity.MotionPicture
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


data class MovieUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val movies: List<MotionPicture> = emptyList()
)

class MainViewModel(
    savedStateHandle: SavedStateHandle,
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(MovieUiState())

    val uiState: StateFlow<MovieUiState> get() = _uiState

    fun getMovies() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val movies = moviesRepository.getPopularMovies(false)
                _uiState.value =
                    _uiState.value.copy(isLoading = false, error = null, movies = movies)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}