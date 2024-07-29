package com.example.novelfever.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chapter(
    val title: String? = null,
    val url: String? = null,
    val dateUpdate: Long? = null
) : Parcelable
