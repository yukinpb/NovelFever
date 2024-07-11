package com.example.novelfever.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.novelfever.core.util.MockData
import com.example.novelfever.ui.screen.component.ListGenre
import com.example.novelfever.ui.component.ListItem

@Composable
fun HistoryScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ListItem(books = MockData.books, onClick = {}, itemPerRow = 3)
            Spacer(modifier = Modifier.padding(8.dp))
            ListGenre(genres = MockData.genres, onClick = {})
        }
    }
}
