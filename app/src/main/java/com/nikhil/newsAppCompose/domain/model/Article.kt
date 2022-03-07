package com.nikhil.newsAppCompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String? = null,
    val description: String? = null,
    val urlToImage: String? = null
)
