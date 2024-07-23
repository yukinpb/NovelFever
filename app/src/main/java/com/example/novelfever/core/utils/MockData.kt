package com.example.novelfever.core.util

import com.example.novelfever.core.enums.BookSource
import com.example.novelfever.core.model.Book
import com.example.novelfever.core.model.Genre

object MockData {
    val books = listOf(
        Book(
            coverUrl = "https://cmnvymn.com/nettruyen/thumb/tham-hoa-tu-linh-su.jpg",
            title = "Book 1",
            url = "https://example.com/book1",
            latestChapter = "Chapter 1",
            bookSource = BookSource.NETTRUYEN
        ),
        Book(
            coverUrl = "https://cmnvymn.com/nettruyen/thumb/nguyen-ton.jpg",
            title = "Book 2",
            url = "https://example.com/book2",
            latestChapter = "Chapter 2",
            bookSource = BookSource.NETTRUYEN
        ),
        Book(
            coverUrl = "https://dtcdnyacd.com/nettruyen/thumb/ta-la-dai-than-tien.jpg",
            title = "Book 3",
            url = "https://example.com/book3",
            latestChapter = "Chapter 3",
            bookSource = BookSource.NETTRUYEN
        ),
        Book(
            coverUrl = "https://dtcdnyacd.com/nettruyen/thumb/do-de-cua-ta-deu-la-dai-phan-phai.jpg",
            title = "Book 4",
            url = "https://example.com/book4",
            latestChapter = "Chapter 4",
            bookSource = BookSource.NETTRUYEN
        ),
        Book(
            coverUrl = "https://cmnvymn.com/nettruyen/thumb/bach-luyen-thanh-than.jpg",
            title = "Book 5",
            url = "https://example.com/book5",
            latestChapter = "Chapter 5",
            bookSource = BookSource.NETTRUYEN
        ),
        Book(
            coverUrl = "https://cmnvymn.com/nettruyen/thumb/ta-co-999-loai-di-nang.jpg",
            title = "Book 6",
            url = "https://example.com/book6",
            latestChapter = "Chapter 6",
            bookSource = BookSource.NETTRUYEN
        ),
        Book(
            coverUrl = "https://dtcdnyacd.com/nettruyen/thumb/ta-la-nhan-vat-phan-dien-dai-thieu-gia.jpg",
            title = "Book 7",
            url = "https://example.com/book7",
            latestChapter = "Chapter 7",
            bookSource = BookSource.NETTRUYEN
        ),
        Book(
            coverUrl = "https://dtcdnyacd.com/nettruyen/thumb/tinh-giap-hon-tuong.jpg",
            title = "Book 8",
            url = "https://example.com/book8",
            latestChapter = "Chapter 8",
            bookSource = BookSource.NETTRUYEN
        ),
        Book(
            coverUrl = "https://cmnvymn.com/nettruyen/thumb/ta-la-ta-de.jpg",
            title = "Book 9",
            url = "https://example.com/book9",
            latestChapter = "Chapter 9",
            bookSource = BookSource.NETTRUYEN
        ),
    )
    val genres = listOf(
        Genre("Action", "Action"),
        Genre("Adventure", "Adventure"),
        Genre("Comedy", "Comedy"),
        Genre("Drama", "Drama"),
        Genre("Fantasy", "Fantasy"),
        Genre("Historical", "Historical"),
        Genre("Horror", "Horror"),
        Genre("Mystery", "Mystery"),
        Genre("Romance", "Romance"),
        Genre("Sci-fi", "Sci-fi"),
        Genre("Slice of Life", "Slice of Life"),
    )
}