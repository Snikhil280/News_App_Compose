package com.nikhil.newsAppCompose.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nikhil.newsAppCompose.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    fun getArticles(): Flow<List<Article>>

    @Insert
    suspend fun insertArticle(article: Article)

    @Delete
    suspend fun deleteArticles(article: Article)

}