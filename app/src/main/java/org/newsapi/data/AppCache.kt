package org.newsapi.data

import android.content.Context
import org.newsapi.getCountry

object AppCache {
    lateinit var CURRENT_COUNTRY: String
    fun init(context: Context) {
        CURRENT_COUNTRY = getCountry(context)
    }
}