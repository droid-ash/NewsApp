package org.newsapi.ui.top

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapi.api.model.Article
import kotlinx.coroutines.launch
import org.newsapi.getTimestamp
import org.newsapi.repository.TopHeadlinesRepository

class HomeViewModel : ViewModel() {

    val selectedArticle = MutableLiveData<Article>()
    var lastFetchCategory: String? = null

    fun setSelectedArticle(item: Article) {
        selectedArticle.value = item
    }

    private val mutableArticleLiveData = MutableLiveData<List<Article>?>()

    fun fetchTopHeadlines(
        country: String,
        category: String?
    ): LiveData<List<Article>?> {
        if (category == lastFetchCategory) return mutableArticleLiveData

        viewModelScope.launch {
            val articleResponse = TopHeadlinesRepository.getTopHeadlines(country, category)
            val articleList = articleResponse.body()?.articles
            articleList?.map { a ->
                run {
                    a.publishedAt = getTimestamp(a.publishedAt)
                }

            }
            mutableArticleLiveData.value = articleList
        }
        lastFetchCategory = category
        return mutableArticleLiveData
    }
}