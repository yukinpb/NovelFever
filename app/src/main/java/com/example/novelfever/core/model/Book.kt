package com.example.novelfever.core.model

import com.example.novelfever.core.enums.BookSource

data class Book(
    val coverUrl: String? = null,
    val title: String = "",
    val url: String = "",
    val latestChapter: String? = null,
    val bookSource: BookSource = BookSource.METRUYENCHU
)
