package com.example.novelfever.ui.screen.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.novelfever.R
import com.example.novelfever.ui.screen.screen.HomeScreen
import com.example.novelfever.ui.screen.screen.LibraryScreen
import com.example.novelfever.ui.screen.screen.SettingScreen
import com.example.novelfever.ui.screen.screen.HistoryScreen
import com.example.novelfever.ui.screen.screen.UpdateScreen

@Composable
fun NavigationGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController, startDestination = Screen.Home.route, Modifier.padding(paddingValues)) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Update.route) { UpdateScreen(navController) }
        composable(Screen.Library.route) { LibraryScreen(navController) }
        composable(Screen.History.route) { HistoryScreen(navController) }
        composable(Screen.Setting.route) { SettingScreen(navController) }

    }
}

sealed class Screen(val route: String, val selectedIcon: ImageVector, val unselectedIcon: ImageVector, @StringRes val resourceId: Int, val badgeCount: Int?) {
    data object Home: Screen("home", Icons.Filled.Home, Icons.Outlined.Home, R.string.home_screen, null)
    data object Update: Screen("update", Icons.Filled.AutoAwesome, Icons.Outlined.AutoAwesome, R.string.update_screen, null)
    data object Library: Screen("library", Icons.Filled.Book, Icons.Outlined.Book, R.string.library_screen, null)
    data object History: Screen("history", Icons.Filled.Bookmarks, Icons.Outlined.Bookmarks, R.string.history_screen, null)
    data object Setting: Screen("setting", Icons.Filled.Settings, Icons.Outlined.Settings, R.string.setting_screen, null)
}