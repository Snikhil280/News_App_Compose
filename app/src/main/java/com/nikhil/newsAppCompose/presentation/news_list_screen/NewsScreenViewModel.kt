package com.nikhil.newsAppCompose.presentation.news_list_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.newsAppCompose.common.Resource
import com.nikhil.newsAppCompose.domain.model.Article
import com.nikhil.newsAppCompose.domain.use_case.favorite.FavoriteUseCase
import com.nikhil.newsAppCompose.domain.use_case.get_news_list.GetNewsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsScreenViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase,
    private val favoriteUseCase: FavoriteUseCase
): ViewModel() {
    val TAG = "NewsScreenViewModel"
    private val _state = mutableStateOf(ArticleListState())
    val state: State<ArticleListState> = _state

    init {
        getArticles()
    }
    fun getArticles(){
        getNewsListUseCase().onEach { result ->
            when(result){
                is Resource.Failure -> {
                    _state.value = ArticleListState(
                        error = result.throwable.localizedMessage ?: "An unexpected error occurred"
                    )
                    Log.d(TAG, "getArticles: Resource.Failure")
                }
                Resource.Loading -> {
                    _state.value = ArticleListState(isLoading = true)
                    Log.d(TAG, "getArticles: Resource.Loading")
                }
                is Resource.Success -> {
                    _state.value = ArticleListState(articleData = result.value)
                    Log.d(TAG, "getArticles: Resource.Success")
                }
            }

        }.launchIn(viewModelScope)
    }

    fun saveArticle(article: Article){
        viewModelScope.launch {
            favoriteUseCase.addArticle(article)
        }
    }

}