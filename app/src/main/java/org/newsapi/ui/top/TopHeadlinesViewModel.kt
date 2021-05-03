package org.newsapi.ui.top

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.newsapi.COUNTRY_IN
import org.newsapi.api.model.Article

class TopHeadlinesViewModel : ViewModel() {

    private val mutableArticleLiveData = MutableLiveData<List<Article>>()

    val articleLiveData: LiveData<List<Article>> = mutableArticleLiveData

    fun fetchTopHeadlines(country: String,category: String) = viewModelScope.launch {
        TopHeadlinesRepository.getTopHeadlines(country, category).let {
            mutableArticleLiveData.value = it.body()?.articles
        }
    }
}