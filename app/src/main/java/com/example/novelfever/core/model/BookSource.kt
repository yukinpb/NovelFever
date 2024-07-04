package com.example.novelfever.core.model

data class BookSource (
    val name: String? = null,
    val url: String? = null,
    val status: String? = null,
    val type: BookType? = null
)

enum class BookType {
    NOVEL,
    MANGA
}