package com.example.novelfever

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.novelfever.ui.component.CustomBottomAppBar
import com.example.novelfever.ui.component.CustomTopAppBar
import com.example.novelfever.ui.navigation.Screen
import com.example.novelfever.ui.screen.HistoryScreen
import com.example.novelfever.ui.screen.LibraryScreen
import com.example.novelfever.ui.screen.SettingScreen
import com.example.novelfever.ui.screen.UpdateScreen
import com.example.novelfever.ui.screen.home.HomeScreen
import com.example.novelfever.ui.theme.NovelFeverTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NovelFeverTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val pagerState = rememberPagerState(pageCount = {
                    10
                })
                val coroutineScope = rememberCoroutineScope()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            CustomBottomAppBar(pagerState.currentPage) { selectedPage ->
                                coroutineScope.launch {
                                    pagerState.scrollToPage(selectedPage)
                                }
                            }
                        },
                        topBar = {
                            val title = getTitleForPage(this@MainActivity, pagerState.currentPage)
                            CustomTopAppBar(title = title)
                        }
                    ) { innerPadding ->
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.padding(innerPadding)
                        ) { page ->
                            when (page) {
                                0 -> HomeScreen(navController)
                                1 -> UpdateScreen(navController)
                                2 -> LibraryScreen(navController)
                                3 -> HistoryScreen(navController)
                                4 -> SettingScreen(navController)
                            }
                        }
                    }
                }

            }
        }
    }
}

fun getTitleForPage(context: Context, pageIndex: Int): String {
    return when (pageIndex) {
        0 -> context.getString(Screen.Home.resourceId)
        1 -> context.getString(Screen.Update.resourceId)
        2 -> context.getString(Screen.Library.resourceId)
        3 -> context.getString(Screen.History.resourceId)
        4 -> context.getString(Screen.Setting.resourceId)
        else -> context.getString(Screen.Home.resourceId)
    }
}
