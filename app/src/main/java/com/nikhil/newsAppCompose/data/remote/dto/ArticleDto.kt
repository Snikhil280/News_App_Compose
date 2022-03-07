package com.nikhil.newsAppCompose.data.remote.dto

import com.nikhil.newsAppCompose.domain.model.Article

data class ArticleDto(
    val author: String,
    val content: String,
    val description: String?,
    val publishedAt: String,
    val sourceDto: SourceDto,
    val title: String?,
    val url: String,
    val urlToImage: String?
){
    fun toArticle(): Article{
        return Article(
            title = title,
            description = description,
            urlToImage = urlToImage
        )
    }
}