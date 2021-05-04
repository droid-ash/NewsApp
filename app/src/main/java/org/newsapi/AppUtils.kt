package org.newsapi

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(url: String?, errorRes: Int = R.drawable.dummy_image) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .error(errorRes)
        .into(this)
}