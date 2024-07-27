package com.example.novelfever.core.source.scraper

import com.example.novelfever.core.enums.BookSource
import com.example.novelfever.core.model.Book
import com.example.novelfever.core.model.BookDetail
import com.example.novelfever.core.model.Chapter
import com.example.novelfever.core.model.Genre
import com.example.novelfever.core.model.Tag

interface BookScraper {
    abstract val source: BookSource
    suspend fun getGenre(): List<Genre>
    suspend fun getBook(url: String, page: Int?): List<Book>
    suspend fun getTag(): List<Tag>
    suspend fun getBookDetail(book: Book): BookDetail
    suspend fun getChapterList(book: Book): List<Chapter>
    suspend fun getChapterContent(book: Book): List<String>
}