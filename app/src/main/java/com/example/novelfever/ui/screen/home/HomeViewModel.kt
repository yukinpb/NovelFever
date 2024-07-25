package com.example.novelfever.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.novelfever.core.model.Book
import com.example.novelfever.core.model.Genre
import androidx.lifecycle.viewModelScope
import com.example.novelfever.core.response.BookResponse
import com.example.novelfever.ui.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class HomeScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isRefreshing: Boolean = false,
    val genres: List<Genre> = emptyList(),
    val genreSelected: Int = 0,
    val books: Map<Genre, BookResponse> = emptyMap()
)

sealed class HomeScreenEvent {
    data object LoadGenre : HomeScreenEvent()
    data class LoadBook(val index: Int) : HomeScreenEvent()
    data object RefreshData : HomeScreenEvent()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: BookRepository
): ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state

    init {
        handleEvent(HomeScreenEvent.LoadGenre)
    }

    fun handleEvent(event: HomeScreenEvent) {
            when (event) {
                is HomeScreenEvent.LoadGenre -> loadGenre()
                is HomeScreenEvent.LoadBook -> loadBook(event.index)
                is HomeScreenEvent.RefreshData -> {
            }
        }
    }

    private fun loadGenre() {
        viewModelScope.launch {
            _state.value = HomeScreenState(isLoading = true)
            try {
                val genres = repository.getGenre()
                _state.value = _state.value.copy(genres = genres, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isError = true)
            }
        }
    }

    private fun loadBook(index: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val bookResponse = repository.getBook(_state.value.genres[index].url, 1)
                if (bookResponse == null) {
                    _state.value = _state.value.copy(isError = true)
                    return@launch
                }
                _state.value = _state.value.copy(books = _state.value.books + (_state.value.genres[index] to bookResponse), isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isError = true)
            }
        }
    }
}