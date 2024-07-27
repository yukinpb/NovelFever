package com.example.novelfever.core.source.scraper

import android.util.Log
import com.example.novelfever.core.enums.BookSource
import com.example.novelfever.core.enums.BookStatus
import com.example.novelfever.core.enums.BookType
import com.example.novelfever.core.enums.ContentRating
import com.example.novelfever.core.model.Book
import com.example.novelfever.core.model.BookDetail
import com.example.novelfever.core.model.Chapter
import com.example.novelfever.core.model.Genre
import com.example.novelfever.core.model.Tag
import com.example.novelfever.core.utils.DateUtils.convertDateStringToLong
import com.example.novelfever.core.utils.JsoupUtils.getJsoup
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
) : BookScraper {

    override suspend fun getBook(url: String,  page: Int?): List<Book>  {
        val doc = getJsoup("$url?page=$page")
        val elements = doc.select("div.ModuleContent div.items div.row div.item")
        val books = mutableListOf<Book>()
        for (element in elements) {
            val book = Book(
                title = element.select("div.image a").attr("title"),
                coverUrl = element.select("div.image a img")
                    .attr("src"),
                bookSource = source,
                url = element.select("div.image a").attr("href"),
                latestChapter = element.select("li.chapter a").first()?.text()
            )
            books.add(book)
        }
        return books
    }

    override suspend fun getGenre(): List<Genre>  {
        val doc = getJsoup(source.url + "/tim-truyen")
        val genreElements = doc.select("div.ModuleContent div.dropdown-genres select option")
        val genres = mutableListOf<Genre>()
        for (element in genreElements) {
            val genre = Genre(
                name = element.text(),
                url = element.attr("value") ?: element.attr("selected value")
            )
            genres.add(genre)
        }
        return genres
    }

    override suspend fun getTag(): List<Tag> {
        TODO("Not yet implemented")
    }

    override suspend fun getChapterList(book: Book): List<Chapter> {
        val doc = getJsoup(book.url)
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
        return chapters
    }

    override suspend fun getBookDetail(book: Book): BookDetail {
        val doc = getJsoup(book.url)
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
        chapters.addAll(getChapterList(book))
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
        return bookDetail
    }

    override suspend fun getChapterContent(book: Book): List<String> {
        TODO("Not yet implemented")
    }
}
