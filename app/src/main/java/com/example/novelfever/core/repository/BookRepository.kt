package com.example.novelfever.core.repository

import android.app.Application
import android.content.Context
import com.example.novelfever.core.model.Book
import com.example.novelfever.core.model.BookDetail
import com.example.novelfever.core.model.Chapter
import com.example.novelfever.core.model.Genre
import com.example.novelfever.core.source.SourceSelector
import com.example.novelfever.core.source.scraper.BookScraper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface BookRepository {
    suspend fun getGenre(): List<Genre>
    suspend fun getBook(url: String, page: Int?): List<Book>
    suspend fun getBookDetail(book: Book): BookDetail?
    suspend fun getChapterList(book: Book): List<Chapter>
    suspend fun getChapterContent(book: Book): List<String>
}

class BookRepositoryImpl @Inject constructor(
    application: Application
) : BookRepository {
    private val prefs = application.getSharedPreferences("novel_preferences", Context.MODE_PRIVATE)
    private val selectedSource = prefs.getString("source", "nettruyen")

    private val bookSource: BookScraper =
        SourceSelector(application.baseContext).getSelectedSource(selectedSource ?: "hentaivn")

    override suspend fun getGenre(): List<Genre> = withContext(Dispatchers.IO){
        try {
            bookSource.getGenre()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getBook(url: String, page: Int?): List<Book> = withContext(Dispatchers.IO) {
        try {
            bookSource.getBook(url, page)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getBookDetail(book: Book): BookDetail? = withContext(Dispatchers.IO) {
        try {
            bookSource.getBookDetail(book)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getChapterList(book: Book): List<Chapter> {
        TODO("Not yet implemented")
    }

    override suspend fun getChapterContent(book: Book): List<String> {
        TODO("Not yet implemented")
    }
}