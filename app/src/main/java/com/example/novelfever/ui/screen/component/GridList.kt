package com.example.novelfever.ui.screen.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.novelfever.core.model.Book

@Composable
fun ListItem(books: List<Book>, onClick: (Book) -> Unit, itemPerRow: Int) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(itemPerRow), modifier = Modifier.fillMaxWidth()
    ) {
        items(books.size) { index ->
            Item(book = books[index], onClick = { onClick(books[index]) })
        }
    }
}