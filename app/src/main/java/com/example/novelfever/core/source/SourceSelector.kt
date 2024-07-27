package com.example.novelfever.core.source

import android.content.Context
import com.example.novelfever.core.source.scraper.BookScraper
import com.example.novelfever.core.source.scraper.HentaiVNScraper
import com.example.novelfever.core.source.scraper.NetTruyenScraper

class SourceSelector(context: Context) {
    private val sourceMap: Map<String, BookScraper> = mapOf(
        "hentaivn" to HentaiVNScraper(context),
        "nettruyen" to NetTruyenScraper(),
    )

    fun getSelectedSource(selectedSource: String): BookScraper {
        if (selectedSource in sourceMap.keys) {
            return sourceMap[selectedSource]!!
        }
        return sourceMap["nettruyen"]!!
    }
}