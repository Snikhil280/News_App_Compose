package com.nikhil.newsAppCompose.presentation.news_list_screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nikhil.newsAppCompose.presentation.Destinations
import com.nikhil.newsAppCompose.presentation.Destinations.DETAILS_SCREEN
import com.nikhil.newsAppCompose.presentation.composable.*
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NewsListScreen(
    navController: NavController?,
    viewModel: NewsScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Scaffold(
        topBar = {
            AppBar(
                title = "Home",
                Icons.Default.Home
            ) {}
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController!!,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        },
        drawerElevation = 10.dp
    ) {
        LazyNewsColumn(
            articles = state.articleData,
            onItemClick = { article ->
                val encodedUrl =
                    URLEncoder.encode(article.urlToImage, StandardCharsets.UTF_8.toString())
                navController?.navigate(
                    "${DETAILS_SCREEN}/${article.title}/${article.description}/${encodedUrl}"
                )
            },
            actionText = "Add as favorite",
            actionIcon = Icons.Default.FavoriteBorder,
            actionOnClick = { article->
                viewModel.saveArticle(article)
            }
        )

        if (state.error.isNotBlank()) {
            ErrorMessage(errorMessage = state.error) {
                viewModel.getArticles()
            }
        }

        if (state.isLoading) {
            CircularProgress()
        }

    }
}




//@InternalCoroutinesApi
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    NewsAppComposeTheme {
//        NewsListScreen(null)
//    }
//}