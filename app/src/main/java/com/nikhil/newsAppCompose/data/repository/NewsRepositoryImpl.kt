package com.nikhil.newsAppCompose.data.repository

import com.nikhil.newsAppCompose.data.local.dao.ArticleDao
import com.nikhil.newsAppCompose.data.remote.api.NewsApi
import com.nikhil.newsAppCompose.domain.model.Article
import com.nikhil.newsAppCompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val articleDao: ArticleDao
) : NewsRepository{

    override suspend fun getNews() = newsApi.getNews()

    override fun getArticles() = articleDao.getArticles()

    override suspend fun insertArticle(article: Article) {
        articleDao.insertArticle(article)
    }

    override suspend fun deleteArticle(article: Article) {
        articleDao.deleteArticles(article)
    }
}