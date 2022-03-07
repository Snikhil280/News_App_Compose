package com.nikhil.newsAppCompose.domain.use_case.favorite

data class FavoriteUseCase(
    val addArticle: AddArticle,
    val deleteArticle: DeleteArticle,
    val getArticles: GetArticles
)