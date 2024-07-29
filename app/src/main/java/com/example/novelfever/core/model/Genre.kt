package com.example.novelfever.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val name: String,
    val url: String
) : Parcelable