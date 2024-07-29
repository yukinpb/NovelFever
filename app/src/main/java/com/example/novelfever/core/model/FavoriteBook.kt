package com.example.novelfever.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Date

@Parcelize
data class FavoriteBook(
    val book: Book,
    val createAt: Date
) : Parcelable