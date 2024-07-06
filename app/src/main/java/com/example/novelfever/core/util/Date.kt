package com.example.novelfever.core.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Date{
    fun convertDateStringToLong(dateString: String): Long {
        return try {
            val dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
            val date: Date? = dateFormat.parse(dateString)
            date!!.time
        } catch (e: Exception) {
            println("Error parsing date: ${e.message}")
            0L // Return 0L or another default value in case of error
        }
    }
}
