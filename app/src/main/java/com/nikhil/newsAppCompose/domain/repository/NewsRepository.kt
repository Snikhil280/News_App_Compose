package com.nikhil.newsAppCompose.domain.repository

import com.nikhil.newsAppCompose.data.remote.dto.NewsDto
import com.nikhil.newsAppCompose.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(): NewsDto

    fun getArticles(): Flow<List<Article>>

    suspend fun insertArticle(article: Article)

    suspend fun deleteArticle(article: Article)
}