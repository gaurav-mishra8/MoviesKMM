package com.greenbot.movieskmm.android.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.greenbot.movieskmm.cache.DatabaseDriverFactory
import com.greenbot.movieskmm.MoviesRepository
import com.greenbot.movieskmm.MoviesRepositoryImpl
import com.greenbot.movieskmm.entity.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


data class MovieUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val movies: List<Movie> = emptyList()
)

class MovieViewModel(
    savedStateHandle: SavedStateHandle,
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(MovieUiState())

    val uiState: StateFlow<MovieUiState> get() = _uiState

    fun getPopularMovies() {
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

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras
                val savedStateHandle = extras.createSavedStateHandle()

                return MovieViewModel(
                    savedStateHandle,
                    MoviesRepositoryImpl(DatabaseDriverFactory(application.applicationContext))
                ) as T
            }
        }
    }
}