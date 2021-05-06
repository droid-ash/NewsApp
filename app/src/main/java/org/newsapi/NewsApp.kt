package org.newsapi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.newsapi.data.AppCache

@HiltAndroidApp
class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()

        AppCache.init(this)
    }
}