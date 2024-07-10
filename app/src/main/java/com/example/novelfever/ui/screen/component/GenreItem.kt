package com.example.novelfever.ui.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.novelfever.core.model.Genre

@Composable
fun GenreItem(genre: Genre, onClick: (Genre) -> Unit){
    Card(
        modifier = Modifier
            .padding(2.dp)
            .clickable { onClick(genre) }
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Text(
            text = genre.name,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
fun GenreItemPreview(){
    val genre = Genre("Action", "Action")
    GenreItem(genre = genre, onClick = {})
}