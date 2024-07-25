package com.example.novelfever.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.novelfever.ui.component.GenreTabRow
import com.example.novelfever.ui.component.ListItem
import com.example.novelfever.ui.custom.ErrorIndicator
import com.example.novelfever.ui.custom.LoadingIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    val pagerState = rememberPagerState {
        state.genres.size
    }
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    var initialLoadDone by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(state.genres, state.isLoading) {
        if (!state.isLoading && state.genres.isNotEmpty() && !initialLoadDone) {
            viewModel.handleEvent(HomeScreenEvent.LoadBook(0))
            initialLoadDone = true
        }
    }

    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.genres.isNotEmpty()) {
                GenreTabRow(
                    genres = state.genres,
                    selectedTabIndex = selectedTabIndex,
                    onGenreSelected = { index ->
                        viewModel.handleEvent(HomeScreenEvent.LoadBook(index))
                        selectedTabIndex = index
                    }
                )
            }
            when {
                state.isLoading -> LoadingIndicator()
                state.isError -> ErrorIndicator()
                else -> {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) { page ->
                        state.genres.getOrNull(page)?.let { genre ->
                            state.books[genre]?.let { bookResponse ->
                                ListItem(books = bookResponse.listBook, onClick = {}, itemPerRow = 3)
                            }
                        }
                    }
                }
            }
        }
    }
}
