package com.nikhil.newsAppCompose.presentation.favorite_screen

import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nikhil.newsAppCompose.presentation.Destinations
import com.nikhil.newsAppCompose.presentation.composable.*
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun FavouriteScreen(
    navController: NavController?,
    viewModel: FavoriteScreenViewModel = hiltViewModel()
) {
    val articleSate = viewModel.articleState.value
    Scaffold(
        topBar = {
            AppBar(title = "Favourite Screen", imageVector = Icons.Default.ArrowBack) {
                navController?.navigateUp()
            }
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController!!,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ){
        LazyNewsColumn(
            articles = articleSate.articles,
            onItemClick = { article ->
                val encodedUrl =
                    URLEncoder.encode(article.urlToImage, StandardCharsets.UTF_8.toString())
                navController?.navigate(
                    "${Destinations.DETAILS_SCREEN}/${article.title}/${article.description}/${encodedUrl}"
                )
            },
            actionText = "Delete",
            actionIcon = Icons.Default.Delete,
            actionOnClick = { article->
                viewModel.deleteArticle(article)
            }
        )

        if (articleSate.articles.isEmpty()) {
            ErrorMessage(errorMessage = "No Data Found")
        }

        if (!articleSate.isArticlesVisible) {
            CircularProgress()
        }
    }
}