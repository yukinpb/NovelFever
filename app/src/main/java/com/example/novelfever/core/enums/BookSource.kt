package com.example.novelfever.core.enums

import com.example.novelfever.core.model.Genre

sealed class BookSource(
    val title: String,
    val locale: String,
    val isBroken: Boolean,
    val url: String,
    val type : BookType,
    val genres : MutableList<Genre> ?= mutableListOf()
) {
    // Optionally, you can add functions or properties specific to the enum here
    data object ASURA : BookSource("AsuraScan", "en", false, "https://asuratoon.com/",BookType.MANGA)
    data object MANGAPARK : BookSource("MangaPark", "en", false, "https://mangapark.net/", BookType.MANGA)
    data object NETTRUYEN : BookSource("NetTruyen", "vi", false, "https://nettruyenviet.com/", BookType.MANGA)
    data object NOVELFULL : BookSource("NovelFull", "en", false, "https://novelfull.net/", BookType.NOVEL)
}
