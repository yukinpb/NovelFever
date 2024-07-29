package com.example.novelfever.core.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class ContentRating : Parcelable {
    SUGGESTIVE,
    ADULT,
    SAFE,
    UNKNOWN
}