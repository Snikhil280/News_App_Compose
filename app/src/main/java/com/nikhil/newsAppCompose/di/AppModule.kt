package com.nikhil.newsAppCompose.di

import android.app.Application
import androidx.room.Room
import com.nikhil.newsAppCompose.data.local.NewsDatabase
import com.nikhil.newsAppCompose.data.local.dao.ArticleDao
import com.nikhil.newsAppCompose.data.remote.RemoteDataSource
import com.nikhil.newsAppCompose.data.remote.api.NewsApi
import com.nikhil.newsAppCompose.data.repository.NewsRepositoryImpl
import com.nikhil.newsAppCompose.domain.repository.NewsRepository
import com.nikhil.newsAppCompose.domain.use_case.favorite.AddArticle
import com.nikhil.newsAppCompose.domain.use_case.favorite.DeleteArticle
import com.nikhil.newsAppCompose.domain.use_case.favorite.FavoriteUseCase
import com.nikhil.newsAppCompose.domain.use_case.favorite.GetArticles
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAuthApi(
        remoteDataSource: RemoteDataSource
    ): NewsApi {
        return remoteDataSource.buildApi(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        app: Application
    ): NewsDatabase{
        return Room.databaseBuilder(
            app,
            NewsDatabase::class.java,
            "news_app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(newsDatabase: NewsDatabase) = newsDatabase.articleDao()

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        articleDao: ArticleDao
    ): NewsRepository{
        return NewsRepositoryImpl(newsApi, articleDao)
    }

    @Provides
    @Singleton
    fun provideFavoriteUseCase(
        newsRepository: NewsRepository
    ): FavoriteUseCase = FavoriteUseCase(
        addArticle = AddArticle(newsRepository),
        deleteArticle = DeleteArticle(newsRepository),
        getArticles = GetArticles(newsRepository)
    )
}