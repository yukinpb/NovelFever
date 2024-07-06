package com.example.novelfever.core.scraper

import com.example.novelfever.core.enums.BookSource
import com.example.novelfever.core.model.Book
import com.example.novelfever.core.model.BookDetail
import com.example.novelfever.core.model.Chapter
import com.example.novelfever.core.model.Genre
import com.example.novelfever.core.model.Tag
import kotlinx.coroutines.flow.Flow

interface Scraper {
    abstract val source : BookSource
    suspend fun getBook(): Flow<List<Book>>
    suspend fun getGenre(): Flow<List<Genre>>
    suspend fun getTag(): Flow<List<Tag>>
    suspend fun getBookDetail(book: Book): Flow<BookDetail>
    suspend fun getChapterList(book: Book): Flow<List<Chapter>>
    suspend fun getChapterContent(book: Book): Flow<List<String>>
}