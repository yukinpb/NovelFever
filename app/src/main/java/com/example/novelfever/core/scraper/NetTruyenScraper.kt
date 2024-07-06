package com.example.novelfever.core.scraper

import com.example.novelfever.core.enums.BookSource
import com.example.novelfever.core.enums.BookStatus
import com.example.novelfever.core.enums.BookType
import com.example.novelfever.core.enums.ContentRating
import com.example.novelfever.core.model.Book
import com.example.novelfever.core.model.BookDetail
import com.example.novelfever.core.model.Chapter
import com.example.novelfever.core.model.Genre
import com.example.novelfever.core.model.Tag
import com.example.novelfever.core.util.Date.convertDateStringToLong
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.select.Elements

class NetTruyenScraper(
    override val source: BookSource = BookSource.NETTRUYEN
) : Scraper {

    override suspend fun getBook(): Flow<List<Book>> = flow {
        val doc = withContext(Dispatchers.IO) {
            Jsoup.connect(source.url).userAgent("Mozilla/5.0").get()
        }
        val elements = doc.select("div.container div.items div.item")

        println("elements size: ${elements.size}")
        val books = mutableListOf<Book>()

        for (element in elements) {
            val book = Book(
                title = element.select("h3 a").text(),
                coverUrl = "https://" + element.select("div.image a img")
                    .attr("data-original"),
                bookSource = source,
                url = element.select("h3 a").attr("href"),
            )
            books.add(book)
        }
        emit(books)
    }.flowOn(Dispatchers.IO) // Apply the flowOn operator here

    override suspend fun getGenre(): Flow<List<Genre>> = flow {
        val doc = withContext(Dispatchers.IO) {
            Jsoup.connect(source.url).userAgent("Mozilla/5.0").get()
        }
        val genreElements = doc.select("div.ModuleContent ul.main-menu div.col-sm-3 li")
        val genres = mutableListOf<Genre>()
        for (element in genreElements) {
            val genre = Genre(
                name = element.select("a strong").text(),
                url = element.select("a").attr("href")
            )
            genres.add(genre)
        }
        emit(genres)
    }.flowOn(Dispatchers.IO) // Apply the flowOn operator here

    override suspend fun getTag(): Flow<List<Tag>> {
        TODO("Not yet implemented")
    }

    override suspend fun getChapterList(book: Book): Flow<List<Chapter>> = flow {
        val doc = withContext(Dispatchers.IO) {
            Jsoup.connect(book.url).userAgent("Mozilla/5.0").get()
        }
        val chapterElements = doc.select("div.list-chapter ul li")
        val chapters = mutableListOf<Chapter>()
        var chapterDetail: Elements
        var title = ""
        var chapterUrl = ""
        var dateUpdate = ""
        for (element in chapterElements) {
            chapterDetail = element.select("div")
            title = chapterDetail[0].text()
            chapterUrl = chapterDetail[0].select("a").attr("href")
            dateUpdate = chapterDetail[1].text()
            chapters.add(
                Chapter(
                    title = title,
                    url = chapterUrl,
                    dateUpdate = convertDateStringToLong(dateUpdate)
                )
            )
        }
        emit(chapters)
    }.flowOn(Dispatchers.IO) // Apply the flowOn operator here

    override suspend fun getBookDetail(book: Book): Flow<BookDetail> = flow {
        val doc = withContext(Dispatchers.IO) {
            book.url.let { Jsoup.connect(it).userAgent("Mozilla/5.0").get() }
        }
        var altTitle = ""
        var author = ""
        var status = ""
        val genres = mutableListOf<Genre>()
        val detailInfo = doc.select("div.detail-info div.col-info ul.list-info li")
        if (detailInfo.size == 5) {
            altTitle = detailInfo[0].select("h2").text()
            author = detailInfo[1].select("p.col-xs-8").text()
            status = detailInfo[2].select("p.col-xs-8").text()
            val genresAnchor = detailInfo[3].select("p.col-xs-8 a")
            for (genre in genresAnchor) {
                genres.add(Genre(name = genre.text(), url = genre.attr("href")))
            }
        } else {
            author = detailInfo[0].select("p.col-xs-8").text()
            status = detailInfo[1].select("p.col-xs-8").text()
            val genresAnchor = detailInfo[2].select("p.col-xs-8 a")
            for (genre in genresAnchor) {
                genres.add(Genre(name = genre.text(), url = genre.attr("href")))
            }
        }
        val description = doc.select("div.detail-content p").text()
        val chapters = mutableListOf<Chapter>()
        getChapterList(book).collect { list ->
            chapters.addAll(list)
        }
        val bookDetail = BookDetail(
            book = book,
            altTitle = altTitle,
            author = author,
            status = when (status) {
                "Hoàn thành" -> BookStatus.COMPLETED
                "Đang tiến hành" -> BookStatus.ONGOING
                else -> BookStatus.UNKNOWN
            },
            genres = genres,
            description = description,
            chapters = chapters,
            rating = if (genres.any { it.name == "Adult" }) ContentRating.ADULT else ContentRating.SAFE,
            type = BookType.MANGA
        )
        emit(bookDetail)
    }.flowOn(Dispatchers.IO) // Apply the flowOn operator here

    override suspend fun getChapterContent(book: Book): Flow<List<String>> {
        TODO("Not yet implemented")
    }
}

fun main() = runBlocking<Unit> {
    val netTruyenScraper = NetTruyenScraper()
    netTruyenScraper.getBook().collect{ list ->
        list.forEach{ manga ->
            println(manga)
        }
    }
}