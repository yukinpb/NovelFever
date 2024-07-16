package com.example.novelfever.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.novelfever.core.model.Genre
import com.example.novelfever.core.util.MockData

@Composable
fun GenreTabRow(
    genres: List<Genre>, selectedGenre: Genre?, onGenreSelected: (Genre) -> Unit
) {
    val scrollState = rememberScrollState()
    TabRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .horizontalScroll(scrollState), selectedTabIndex = 0
    ) {
        genres.forEachIndexed { _, genre ->
            Tab(
                selected = selectedGenre == genre,
                onClick = { onGenreSelected(genre) },
                modifier = Modifier.background(
                    color = Color.White
                )
            ) {
                Text(
                    text = genre.name,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    modifier = Modifier.padding(4.dp),
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewTabRow() {
    GenreTabRow(
        genres = MockData.genres,
        selectedGenre = MockData.genres.first(),
        onGenreSelected = {})
}