package com.example.novelfever.core.model

import com.example.novelfever.core.enums.ContentRating
import com.example.novelfever.core.enums.BookStatus
import com.example.novelfever.core.enums.BookType

data class BookDetail(
    val book: Book,
    val largeCoverUrl: String? = null,
    val author: String? = null,
    val altTitle: String? = null,
    val status: BookStatus,
    val genres: List<Genre>? = null,
    val tags: List<Tag>? = null,
    val rating : ContentRating,
    val description: String? = null,
    val chapters: List<Chapter>? = null,
    val type : BookType
)