package com.cherish.nasasearchapp.common

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    Glide.with(this)
        .load(url)
        .into(this)
}
//@BindingAdapter("date")
//fun TextView.convertDate(): String {
//    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
//    val outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//    val localDate = LocalDate.parse(this.toString(), formatter)
//    return outputFormat.format(localDate)
//}