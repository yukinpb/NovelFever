package com.example.novelfever.core.model

import java.sql.Date

data class FavoriteBook(
    val book: Book,
    val createAt: Date
)