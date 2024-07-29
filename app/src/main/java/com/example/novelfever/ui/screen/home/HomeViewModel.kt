package com.example.novelfever.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novelfever.core.model.Book
import com.example.novelfever.core.model.Genre
import com.example.novelfever.ui.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isRefreshing: Boolean = false,
    val isLoadMore: Boolean = false,
    val genres: List<Genre> = emptyList(),
    val genreSelected: Int = 0,
    val books: List<BookDisplay> = listOf()
)

data class BookDisplay(
    val genre: Genre,
    val books: List<Book>,
    val currentPage: Int
)

sealed class HomeScreenEvent {
    data object LoadGenre : HomeScreenEvent()
    data class LoadBook(val index: Int, val page: Int) : HomeScreenEvent()
    data object RefreshData : HomeScreenEvent()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state

    init {
        handleEvent(HomeScreenEvent.LoadGenre)
    }

    fun handleEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.LoadGenre -> loadGenre()
            is HomeScreenEvent.LoadBook -> loadBook(event.index, event.page)
            is HomeScreenEvent.RefreshData -> {
            }
        }
    }

    private fun loadGenre() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val genres = repository.getGenre()
                _state.value = _state.value.copy(genres = genres)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isError = true)
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }

    private fun loadBook(index: Int, page: Int) {
        viewModelScope.launch {
            if (page == 1) {
                _state.value = _state.value.copy(isLoading = true)
            } else {
                _state.value = _state.value.copy(isLoadMore = true)
            }

            try {
                val genre = _state.value.genres[index]
                val bookResponse = repository.getBook(genre.url, page)
                if (bookResponse.isEmpty()) {
                    _state.value = _state.value.copy(isError = true)
                    return@launch
                }
                val updatedBooks = _state.value.books.toMutableList()
                val bookDisplayIndex = updatedBooks.indexOfFirst { it.genre == genre }
                if (bookDisplayIndex != -1) {
                    val currentBooks = updatedBooks[bookDisplayIndex].books.toMutableList()
                    currentBooks.addAll(bookResponse)
                    updatedBooks[bookDisplayIndex] = updatedBooks[bookDisplayIndex].copy(
                        books = currentBooks,
                        currentPage = page
                    )
                } else {
                    updatedBooks.add(BookDisplay(genre, bookResponse, page))
                }
                _state.value = _state.value.copy(books = updatedBooks)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isError = true)
            } finally {
                _state.value = _state.value.copy(isLoading = false, isLoadMore = false)
            }
        }
    }
}