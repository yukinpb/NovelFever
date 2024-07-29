package com.example.novelfever.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.novelfever.core.enums.Screen
import com.example.novelfever.ui.screen.detail.DetailScreen
import com.example.novelfever.ui.screen.main.MainScreen
import com.example.novelfever.ui.screen.main.SharedViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
) {
    val sharedViewModel: SharedViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            MainScreen(navController, sharedViewModel)
        }
        composable(Screen.Detail.route) {
            DetailScreen(navController, sharedViewModel)
        }
    }
}

