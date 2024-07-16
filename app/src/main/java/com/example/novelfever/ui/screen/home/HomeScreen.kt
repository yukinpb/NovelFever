package com.example.novelfever.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.novelfever.core.util.MockData
import com.example.novelfever.ui.component.GenreTabRow
import com.example.novelfever.ui.component.ListItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val pagerState = rememberPagerState {
        MockData.genres.size
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GenreTabRow(genres = MockData.genres,
                selectedGenre = MockData.genres.first(),
                onGenreSelected = {})
            HorizontalPager(state = pagerState) {
                ListItem(books = MockData.books, onClick = {})
            }
        }
    }
}