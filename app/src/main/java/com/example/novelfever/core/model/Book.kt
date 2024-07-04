package com.example.novelfever.core.model

data class Book(
    val coverUrl: String? = null,
    val title: String? = null,
    val url: String? = null,
    val latestChapter: String? = null,
    val bookSource: BookSource? = null
)
