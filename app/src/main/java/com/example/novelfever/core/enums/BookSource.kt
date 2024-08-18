package com.example.novelfever.core.enums

import android.os.Parcelable
import com.example.novelfever.core.model.Genre
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class BookSource(
    val title: String,
    val locale: String,
    val isBroken: Boolean,
    val url: String,
    val type : BookType,
    val genres : MutableList<Genre> ?= mutableListOf()
) : Parcelable {
    data object ASURA : BookSource("AsuraScan", "en", false, "https://asuratoon.com",BookType.MANGA)
    data object MANGAPARK : BookSource("MangaPark", "en", false, "https://mangapark.net", BookType.MANGA)
    data object NETTRUYEN : BookSource("NetTruyen", "vi", false, "https://nettruyenww.com", BookType.MANGA)
    data object NOVELFULL : BookSource("NovelFull", "en", false, "https://novelfull.net", BookType.NOVEL)
    data object HENTAIVN : BookSource("HentaiVN", "vi", false, "https://hentaivn.homes", BookType.MANGA)
}
