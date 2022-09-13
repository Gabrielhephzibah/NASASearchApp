package com.cherish.nasasearchapp.common.extension

import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


fun String.convertDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    val outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
    val localDate = LocalDate.parse(this, formatter)
    return outputFormat.format(localDate)
}
