package com.nikhil.newsAppCompose.presentation.news_list_screen

import com.nikhil.newsAppCompose.domain.model.Article

data class ArticleListState (
    val isLoading: Boolean = false,
    val articleData: List<Article> = emptyList(),
    val error: String = ""
)