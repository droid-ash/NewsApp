package org.newsapi.repository

import com.newsapi.api.model.Article
import com.newsapi.api.service.NewsApi
import org.newsapi.data.db.ArticleDao
import javax.inject.Inject

class NewsRepository @Inject constructor(private val db: ArticleDao, private val api: NewsApi) {

    suspend fun getTopHeadlines(country: String, category: String?) =
        api.getTopHeadlinesByCountry(country, category)

    suspend fun getSearchedHeadlines(query: String?) = api.searchHeadlines(query)

    suspend fun insertArticleInDb(article: Article) = db.insertArticle(article)

    fun getSavedArticles() = db.getAllArticles()

    suspend fun deleteArticle(article: Article) = db.deleteArticle(article)
}