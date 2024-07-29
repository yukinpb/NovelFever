package com.example.novelfever.ui.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.novelfever.core.enums.BookStatus
import com.example.novelfever.core.model.Genre
import com.example.novelfever.ui.component.custom.TopAppBarWithImage
import com.example.novelfever.ui.component.genre.GenreList
import com.example.novelfever.ui.screen.main.SharedViewModel

@Composable
fun DetailScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val book by sharedViewModel.selectedBook.collectAsState()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.bookDetail) {
        if (state.bookDetail == null) {
            book?.let {
                viewModel.handleEvent(DetailScreenEvent.LoadDetail(it))
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBarWithImage(
                imageUrl = state.bookDetail?.largeCoverUrl ?: book?.coverUrl,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        val adjustedTopPadding = innerPadding.calculateTopPadding() - 90.dp
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = adjustedTopPadding,
                    start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                    end = innerPadding.calculateEndPadding(LayoutDirection.Ltr),
                    bottom = innerPadding.calculateBottomPadding()
                )
                .zIndex(1f)
        ) {
            Column {
                DetailHeader(
                    imageUrl = state.bookDetail?.largeCoverUrl ?: book?.coverUrl,
                    title = book?.title ?: "No Title",
                    author = state.bookDetail?.author ?: "Unknown Author",
                    status = state.bookDetail?.status ?: BookStatus.UNKNOWN
                )
                DetailBody(
                    genres = state.bookDetail?.genres ?: emptyList(),
                    description = state.bookDetail?.description ?: "No Description"
                )
            }
        }
    }
}

@Composable
fun DetailHeader(imageUrl: String?, title: String, author: String, status: BookStatus) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .width(100.dp)
                .height(150.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                imageUrl?.let {
                    AsyncImage(
                        model = it,
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
            )
            Text(
                text = author,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            StatusRow(status = status)
        }
    }
}

@Composable
fun StatusRow(status: BookStatus) {
    Card(
        modifier = Modifier.padding(top = 32.dp, start = 2.dp, end = 0.dp, bottom = 0.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
    ) {
        Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
            val statusName = when (status) {
                BookStatus.COMPLETED -> "Completed"
                BookStatus.ONGOING -> "Ongoing"
                BookStatus.UPCOMING -> "Upcoming"
                BookStatus.UNKNOWN -> "Unknown"
                BookStatus.ABANDONED -> "Abandoned"
                BookStatus.PAUSED -> "Paused"
            }
            Text(
                text = statusName,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}

@Composable
fun DetailBody(genres: List<Genre>, description: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ActionButton(icon = Icons.Filled.Favorite, text = "Favorite")
                ActionButton(icon = Icons.Filled.List, text = "Chapter List")
                ActionButton(icon = Icons.Filled.MenuBook, text = "Read")
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        GenreList(genres = genres)
        Spacer(modifier = Modifier.size(16.dp))
        DescriptionCard(description = description)
    }
}

@Composable
fun ActionButton(icon: ImageVector, text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun DescriptionCard(description: String) {
    Card(
        modifier = Modifier.fillMaxSize(),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

