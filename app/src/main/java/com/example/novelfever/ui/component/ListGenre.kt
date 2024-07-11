package com.example.novelfever.ui.screen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.novelfever.core.model.Genre

@Composable
fun ListGenre(genres : List<Genre>, onClick: (Genre) -> Unit){
    LazyRow(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        items(genres.size) { index ->
            GenreItem(genre = genres[index], onClick = { onClick(genres[index]) })
        }
    }
}