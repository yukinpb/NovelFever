package com.example.novelfever.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryBook(
    val book: Book,
    val lastReadChapter: Chapter
) : Parcelable