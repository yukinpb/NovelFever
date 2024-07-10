package com.example.novelfever.core.response

import com.example.novelfever.core.model.Book

data class BookResponse(
    val listBook: List<Book> = mutableListOf(),
    val nextPageUrl: String = ""
)