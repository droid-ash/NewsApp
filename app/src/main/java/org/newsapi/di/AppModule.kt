package org.newsapi.di

import android.content.Context
import com.newsapi.api.NewsApiClient
import com.newsapi.api.service.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.newsapi.repository.NewsRepository
import org.newsapi.data.db.ArticleDao
import org.newsapi.data.db.ArticleDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        ArticleDatabase.invoke(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: ArticleDatabase) = db.articleDao()

    @Singleton
    @Provides
    fun provideRepository(localDataSource: ArticleDao, api: NewsApi) =
        NewsRepository(localDataSource, api)

    @Singleton
    @Provides
    fun provideNewsApi() = NewsApiClient().client
}