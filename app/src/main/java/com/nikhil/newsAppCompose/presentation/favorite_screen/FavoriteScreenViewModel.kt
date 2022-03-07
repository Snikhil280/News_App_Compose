package com.nikhil.newsAppCompose.presentation.favorite_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.newsAppCompose.domain.model.Article
import com.nikhil.newsAppCompose.domain.use_case.favorite.FavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase
): ViewModel() {
    private val _articleState = mutableStateOf(ArticleState())
    val articleState: State<ArticleState> = _articleState

    private var getArticlesJob: Job ? = null

    init {
        getArticles()
    }

    fun getArticles(){
        getArticlesJob?.cancel()
        getArticlesJob = favoriteUseCase.getArticles().onEach { articles->
            print("hello nikhil $articles")
            _articleState.value = ArticleState(
                articles = articles,
                isArticlesVisible = true
            )
        }.launchIn(viewModelScope)
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch {
            favoriteUseCase.deleteArticle(article)
        }
    }
}