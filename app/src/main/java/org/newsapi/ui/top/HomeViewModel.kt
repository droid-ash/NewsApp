package org.newsapi.ui.top

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapi.api.model.Article
import com.newsapi.api.model.NewsApiResponse
import kotlinx.coroutines.launch
import org.newsapi.getTimestamp
import retrofit2.Response


class HomeViewModel : ViewModel() {

    val selectedArticle = MutableLiveData<Article>()

    fun setSelectedArticle(item: Article) {
        selectedArticle.value = item
    }

    private val mutableArticleLiveData = MutableLiveData<List<Article>?>()
    val articleLiveData: LiveData<List<Article>?> = mutableArticleLiveData

    fun fetchTopHeadlines(country: String, category: String) = viewModelScope.launch {
        val articleResponse = TopHeadlinesRepository.getTopHeadlines(country, category)
        val articleList = getListFromResponse(articleResponse)
        mutableArticleLiveData.value = articleList
    }

    private fun getListFromResponse(it: Response<NewsApiResponse>): List<Article>? {
        val articles = it.body()?.articles
        articles?.map { a -> { a.publishedAt = getTimestamp(a.publishedAt) } }
        return articles
    }
}