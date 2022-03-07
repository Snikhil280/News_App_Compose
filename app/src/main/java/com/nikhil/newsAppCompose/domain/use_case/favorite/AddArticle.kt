package com.nikhil.newsAppCompose.domain.use_case.favorite

import com.nikhil.newsAppCompose.domain.model.Article
import com.nikhil.newsAppCompose.domain.repository.NewsRepository
import javax.inject.Inject

class AddArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article){
        newsRepository.insertArticle(article)
    }
}