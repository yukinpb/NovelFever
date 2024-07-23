package com.example.novelfever.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ScrollableTabRow
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
    genres: List<Genre>, selectedTabIndex: Int, onGenreSelected: (Int) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        edgePadding = 0.dp,
    ) {
        genres.forEachIndexed { index, genre ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onGenreSelected(index) },
                text = { Text(text = genre.name) }
            )
        }
    }
}

@Composable
@Preview
fun PreviewTabRow() {

}