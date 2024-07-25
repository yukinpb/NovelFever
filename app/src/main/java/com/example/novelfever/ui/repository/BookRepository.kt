package com.example.novelfever.ui.repository

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import com.example.novelfever.core.model.Book
import com.example.novelfever.core.model.BookDetail
import com.example.novelfever.core.model.Chapter
import com.example.novelfever.core.model.Genre
import com.example.novelfever.core.model.SourceSelector
import com.example.novelfever.core.response.BookResponse
import com.example.novelfever.core.scraper.BookScraper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface BookRepository {
    suspend fun getGenre(): List<Genre>
    suspend fun getBook(url: String, page: Int?): BookResponse?
    suspend fun getBookDetail(book: Book): BookDetail?
    suspend fun getChapterList(book: Book): List<Chapter>
    suspend fun getChapterContent(book: Book): List<String>
}

class BookRepositoryImpl @Inject constructor(
    application: Application
) : BookRepository {
    private val prefs = application.getSharedPreferences("novel_preferences", Context.MODE_PRIVATE)
    private val selectedSource = prefs.getString("source", "hentaivn")

    private val bookSource: BookScraper =
        SourceSelector(application as Context).getSelectedSource(selectedSource ?: "hentaivn")

    override suspend fun getGenre(): List<Genre> = withContext(Dispatchers.IO){
        try {
            bookSource.getGenre()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getBook(url: String, page: Int?): BookResponse? = withContext(Dispatchers.IO) {
        try {
            bookSource.getBook(url, page)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getBookDetail(book: Book): BookDetail? {
        TODO("Not yet implemented")
    }

    override suspend fun getChapterList(book: Book): List<Chapter> {
        TODO("Not yet implemented")
    }

    override suspend fun getChapterContent(book: Book): List<String> {
        TODO("Not yet implemented")
    }
}