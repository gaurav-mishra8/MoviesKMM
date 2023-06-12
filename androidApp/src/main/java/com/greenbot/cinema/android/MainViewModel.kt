package com.greenbot.cinema.android

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.greenbot.cinema.MoviesSDK
import com.greenbot.cinema.cache.DatabaseDriverFactory
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
    private val moviesSDK: MoviesSDK,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _uiState = MutableStateFlow(MovieUiState())

    val uiState: StateFlow<MovieUiState> get() = _uiState

    fun getMovies() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val movies = moviesSDK.getPopularMovies(false)
                _uiState.value =
                    _uiState.value.copy(isLoading = false, error = null, movies = movies)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                val savedStateHandle = extras.createSavedStateHandle()
                val moviesSdk = MoviesSDK(DatabaseDriverFactory(application.applicationContext))
                return MainViewModel(moviesSdk, savedStateHandle) as T
            }
        }
    }


}