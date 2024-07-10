package com.example.novelfever.ui.screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.novelfever.core.enums.BookSource
import com.example.novelfever.core.model.Book

@Composable
fun Item(book: Book, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clickable { onClick() }
            .clip(RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Column(
            modifier = modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = book.coverUrl,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.75f)
                    .clip(RoundedCornerShape(20.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = book.title,
                maxLines = 2,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = book.latestChapter ?: "Unknown Chapter",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    Item(book = Book(
        title = "Cao Võ Hạ Canh Đến Một Vạn Năm Sau",
        coverUrl = "https://dtcdnyacd.com/nettruyen/thumb/cao-vo-ha-canh-den-mot-van-nam-sau.jpg",
        bookSource = BookSource.NETTRUYEN,
        latestChapter = "Chap 101",
        url = "https://example.com"
    ), onClick = {})
}
