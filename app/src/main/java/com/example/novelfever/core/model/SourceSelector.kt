package com.example.novelfever.core.model

import android.content.Context
import com.example.novelfever.core.scraper.BookScraper
import com.example.novelfever.core.scraper.HentaiVNScraper

class SourceSelector(context: Context) {
    private val sourceMap: Map<String, BookScraper> = mapOf(
        "hentaivn" to HentaiVNScraper(context)
    )

    fun getSelectedSource(selectedSource: String): BookScraper {
        if (selectedSource in sourceMap.keys) {
            return sourceMap[selectedSource]!!
        }
        return sourceMap["hentaivn"]!!
    }
}