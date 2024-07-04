package com.example.novelfever.core.model

data class BookDetail(
    val title: String? = null,
    val largeCoverUrl: String? = null,
    val author: String? = null,
    val altTitle: String? = null,
    val status: String? = null,
    val genre: String? = null,
    val description: String? = null,
    val listChapters: List<Chapter>? = null,
)