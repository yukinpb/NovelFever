package com.example.novelfever.core.enums

sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object Detail : Screen("detail")
}