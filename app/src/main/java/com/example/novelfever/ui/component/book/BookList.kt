package com.example.novelfever.ui.component.book

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.novelfever.core.model.Book
import com.example.novelfever.ui.screen.component.book.BookItem

@Composable
fun BookList(
    books: List<Book>,
    modifier: Modifier,
    onBookSelected: (Book) -> Unit,
    onLoadMore: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier
    ) {
        items(books.size) { index ->
            BookItem(
                book = books[index],
                modifier = Modifier.fillMaxSize() // Ensure the item fills the grid cell
            ) {
                onBookSelected(books[index])
            }

            if (index == books.size - 1) {
                LaunchedEffect(Unit) {
                    onLoadMore()
                }
            }
        }
    }
}