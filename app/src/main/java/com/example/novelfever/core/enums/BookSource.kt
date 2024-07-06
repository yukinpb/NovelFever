package com.example.novelfever.core.enums

enum class BookSource(
    val title: String,
    val locale: String,
    val isBroken: Boolean,
    val url: String,
    val type : BookType
) {
    ASURA("AsuraScan", "en", false, "https://asuratoon.com/",BookType.MANGA),
    MANGAPARK("MangaPark", "en", false, "https://mangapark.net/", BookType.MANGA),
    NETTRUYEN("NetTruyen", "vi", false, "https://nettruyenviet.com/", BookType.MANGA),
    NOVELFULL("NovelFull", "en", false, "https://novelfull.net/", BookType.NOVEL)

    // Optionally, you can add functions or properties specific to the enum here
}
