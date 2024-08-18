package com.example.novelfever.core.source

import android.content.Context
import com.example.novelfever.core.source.scraper.BookScraper
import com.example.novelfever.core.source.scraper.HentaiVNScraper
import com.example.novelfever.core.source.scraper.NetTruyenScraper

class SourceSelector(
    val context: Context
) {
    private val sourceMap: Map<String, BookScraper> = mapOf(
        "hentaivn" to HentaiVNScraper(),
        "nettruyen" to NetTruyenScraper(context),
    )

    fun getSelectedSource(selectedSource: String): BookScraper {
        if (selectedSource in sourceMap.keys) {
            return sourceMap[selectedSource]!!
        }
        return sourceMap["nettruyen"]!!
    }
}