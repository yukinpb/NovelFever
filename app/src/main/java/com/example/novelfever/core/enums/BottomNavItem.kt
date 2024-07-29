package com.example.novelfever.core.enums

import androidx.annotation.StringRes
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
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.novelfever.R

sealed class BottomNavItem(val route: String, val selectedIcon: ImageVector, val unselectedIcon: ImageVector, @StringRes val resourceId: Int, val badgeCount: Int?) {
    data object Home: BottomNavItem("home", Icons.Filled.Home, Icons.Outlined.Home, R.string.home_screen, null)
    data object Update: BottomNavItem("update", Icons.Filled.AutoAwesome, Icons.Outlined.AutoAwesome, R.string.update_screen, 10)
    data object Library: BottomNavItem("library", Icons.Filled.Book, Icons.Outlined.Book, R.string.library_screen, null)
    data object History: BottomNavItem("history", Icons.Filled.Bookmarks, Icons.Outlined.Bookmarks, R.string.history_screen, null)
    data object Setting: BottomNavItem("setting", Icons.Filled.Settings, Icons.Outlined.Settings, R.string.setting_screen, null)
}