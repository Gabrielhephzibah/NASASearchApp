package com.cherish.nasasearchapp.common

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.cherish.nasasearchapp.common.extensions.convertDate

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    Glide.with(this)
        .load(url)
        .into(this)
}
@BindingAdapter("date")
fun TextView.changeDate(date: String){
    this.text = date.convertDate()
}