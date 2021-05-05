package org.newsapi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapi.api.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.newsapi.repository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

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
            val articleResponse = newsRepository.getTopHeadlines(country, category)
            val articleList = articleResponse.body()?.articles
            mutableArticleLiveData.value = articleList
        }
        lastFetchCategory = category
        return mutableArticleLiveData
    }

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.insertArticleInDb(article)
    }

    fun getSavedNews() = newsRepository.getSavedArticles()
}