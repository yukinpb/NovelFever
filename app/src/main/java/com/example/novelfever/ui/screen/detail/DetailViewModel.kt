package com.example.novelfever.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novelfever.core.model.Book
import com.example.novelfever.core.model.BookDetail
import com.example.novelfever.core.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val bookDetail: BookDetail? = null
)

sealed class DetailScreenEvent {
    data class LoadDetail(val book: Book) : DetailScreenEvent()
}

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailScreenState())
    val state: StateFlow<DetailScreenState> = _state

    fun handleEvent(event: DetailScreenEvent) {
        when (event) {
            is DetailScreenEvent.LoadDetail -> loadDetail(event.book)
        }
    }

    private fun loadDetail(book: Book) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val bookDetail = repository.getBookDetail(book)
                _state.value = _state.value.copy(bookDetail = bookDetail)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isError = true)
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}