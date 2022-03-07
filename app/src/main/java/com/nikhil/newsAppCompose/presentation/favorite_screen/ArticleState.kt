package com.nikhil.newsAppCompose.presentation.favorite_screen

import com.nikhil.newsAppCompose.domain.model.Article

data class ArticleState(
    val articles: List<Article> = emptyList(),
    val isArticlesVisible: Boolean = false
)