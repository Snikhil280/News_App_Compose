package com.nikhil.newsAppCompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikhil.newsAppCompose.data.local.dao.ArticleDao
import com.nikhil.newsAppCompose.domain.model.Article

@Database(
    entities = [Article::class],
    version = 1
)
abstract class NewsDatabase : RoomDatabase(){

    abstract fun articleDao(): ArticleDao
}