package com.example.novelfever.core.scraper

import android.content.Context
import com.example.novelfever.core.enums.BookSource
import com.example.novelfever.core.model.Book
import com.example.novelfever.core.model.BookDetail
import com.example.novelfever.core.model.Chapter
import com.example.novelfever.core.model.Genre
import com.example.novelfever.core.response.BookResponse
import com.example.novelfever.core.utils.JsoupUtils.getJsoupWithCloudflare

class HentaiVNScraper(
    private val context: Context,
    override val source: BookSource = BookSource.HENTAIVN
) : BookScraper {

    override suspend fun getBook(url: String, page: Int?): BookResponse {
        val doc = getJsoupWithCloudflare("$url?page=$page", context)
        val isMobile = doc.select(".header-logo").size != 0
        val next = doc.select(".pagination-footer a.current + a").text()
        val el = doc.select(".main .block-item li.item")
        val data = mutableListOf<Book>()
        el.forEach() {
            val book = Book(
                title = it.select("a").first()?.text() ?: "",
                coverUrl = it.select(if (isMobile) ".box-cover-2 img" else ".box-cover img").first()
                    ?.attr("data-src"),
                bookSource = source,
                url = it.select("a").first()?.attr("href") ?: "",
            )
            data.add(book)
        }
        return BookResponse(data, next)
    }

    override suspend fun getGenre(): List<Genre> {
        val listGenres = listOf(
                Genre("3D Hentai", "${source.url}/the-loai-3-3d_hentai.html"),
                Genre("Action", "${source.url}/the-loai-5-action.html"),
                Genre("Adult", "${source.url}/the-loai-116-adult.html"),
                Genre("Adventure", "${source.url}/the-loai-203-adventure.html"),
                Genre("Ahegao", "${source.url}/the-loai-20-ahegao.html"),
                Genre("Anal", "${source.url}/the-loai-21-anal.html"),
                Genre("Angel", "${source.url}/the-loai-249-angel.html"),
                Genre("Ảnh động", "${source.url}/the-loai-131-anh_dong.html"),
                Genre("Animal", "${source.url}/the-loai-127-animal.html"),
                Genre("Animal girl", "${source.url}/the-loai-22-animal_girl.html"),
                Genre("Artist CG", "${source.url}/the-loai-115-artist.html"),
                Genre("BBM", "${source.url}/the-loai-257-bbm.html"),
                Genre("BBW", "${source.url}/the-loai-251-bbw.html"),
                Genre("BDSM", "${source.url}/the-loai-24-bdsm.html"),
                Genre("Bestiality", "${source.url}/the-loai-25-bestiality.html"),
                Genre("Big Ass", "${source.url}/the-loai-133-big_ass.html")
        )
        return listGenres
    }

    override suspend fun getBookDetail(book: Book): BookDetail {
        TODO("Not yet implemented")
    }

    override suspend fun getChapterList(book: Book): List<Chapter> {
        TODO("Not yet implemented")
    }

    override suspend fun getChapterContent(book: Book): List<String> {
        TODO("Not yet implemented")
    }
}