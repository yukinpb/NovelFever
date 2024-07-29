package com.example.novelfever.core.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class BookStatus : Parcelable {
    COMPLETED,
    ONGOING,
    UPCOMING,
    UNKNOWN,
    ABANDONED,
    PAUSED
}