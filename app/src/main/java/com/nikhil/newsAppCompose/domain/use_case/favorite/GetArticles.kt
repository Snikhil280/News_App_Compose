package com.nikhil.newsAppCompose.domain.use_case.favorite

import com.nikhil.newsAppCompose.domain.repository.NewsRepository
import javax.inject.Inject

class GetArticles(
    private val newsRepository: NewsRepository
) {
    operator fun invoke() = newsRepository.getArticles()

}