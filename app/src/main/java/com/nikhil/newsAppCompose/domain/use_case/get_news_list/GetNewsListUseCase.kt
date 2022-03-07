package com.nikhil.newsAppCompose.domain.use_case.get_news_list

import android.util.Log
import com.nikhil.newsAppCompose.common.Resource
import com.nikhil.newsAppCompose.domain.model.Article
import com.nikhil.newsAppCompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading)
        try {
            val articles = newsRepository.getNews().articles.map { articleDto ->
                articleDto.toArticle()
            }
            Log.d("Nikhil", "${articles.size}" )
            emit(Resource.Success(articles))

        }catch (throwable : Throwable){
            Resource.Failure(throwable)
        }
    }
}