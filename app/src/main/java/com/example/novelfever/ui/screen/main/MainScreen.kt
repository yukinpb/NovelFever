package com.example.novelfever.ui.screen.main

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.novelfever.core.enums.BottomNavItem
import com.example.novelfever.ui.component.custom.CustomBottomAppBar
import com.example.novelfever.ui.component.custom.TopAppBarWithTitle
import com.example.novelfever.ui.screen.HistoryScreen
import com.example.novelfever.ui.screen.LibraryScreen
import com.example.novelfever.ui.screen.SettingScreen
import com.example.novelfever.ui.screen.UpdateScreen
import com.example.novelfever.ui.screen.home.HomeScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Update,
        BottomNavItem.Library,
        BottomNavItem.History,
        BottomNavItem.Setting
    )

    val pagerState = rememberPagerState(pageCount = {
        10
    })
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                CustomBottomAppBar(items, pagerState.currentPage) { selectedPage ->
                    coroutineScope.launch {
                        pagerState.scrollToPage(selectedPage)
                    }
                }
            },
            topBar = {
                val title = getTitleForPage(context, pagerState.currentPage)
                TopAppBarWithTitle(title = title)
            }
        ) { innerPadding ->
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.padding(innerPadding)
            ) { page ->
                when (page) {
                    0 -> HomeScreen(navController, sharedViewModel)
                    1 -> UpdateScreen(navController)
                    2 -> LibraryScreen(navController)
                    3 -> HistoryScreen(navController)
                    4 -> SettingScreen(navController)
                }
            }
        }
    }
}

fun getTitleForPage(context: Context, pageIndex: Int): String {
    return when (pageIndex) {
        0 -> context.getString(BottomNavItem.Home.resourceId)
        1 -> context.getString(BottomNavItem.Update.resourceId)
        2 -> context.getString(BottomNavItem.Library.resourceId)
        3 -> context.getString(BottomNavItem.History.resourceId)
        4 -> context.getString(BottomNavItem.Setting.resourceId)
        else -> context.getString(BottomNavItem.Home.resourceId)
    }
}