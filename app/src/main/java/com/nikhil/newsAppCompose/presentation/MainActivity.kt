package com.nikhil.newsAppCompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.nikhil.newsAppCompose.presentation.Destinations.DETAILS_SCREEN
import com.nikhil.newsAppCompose.presentation.Destinations.FAVOURITE_SCREEN
import com.nikhil.newsAppCompose.presentation.Destinations.NEWS_SCREEN
import com.nikhil.newsAppCompose.presentation.Destinations.PROFILE_SCREEN
import com.nikhil.newsAppCompose.presentation.details_screen.DetailsScreen
import com.nikhil.newsAppCompose.presentation.favorite_screen.FavouriteScreen
import com.nikhil.newsAppCompose.presentation.news_list_screen.NewsListScreen
import com.nikhil.newsAppCompose.presentation.profile_screen.ProfileScreen
import com.nikhil.newsAppCompose.presentation.ui.theme.NewsAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        setContent {
            NewsAppComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NEWS_SCREEN,
                    ) {
                        composable(NEWS_SCREEN) {
                            NewsListScreen(navController)
                        }
                        composable(
                            route = "$DETAILS_SCREEN/{title}/{description}/{urlToImage}"
                        ) {
                            val title = it.arguments?.getString("title")!!
                            val urlToImage = it.arguments?.getString("urlToImage")!!
                            val description = it.arguments?.getString("description")!!
                            DetailsScreen(
                                navController = navController,
                                title = title,
                                urlToImage = urlToImage,
                                description = description
                            )
                        }
                        composable(FAVOURITE_SCREEN) {
                            FavouriteScreen(navController)
                        }
                        composable(PROFILE_SCREEN) {
                            ProfileScreen(navController)
                        }
                    }
                }
            }
        }
    }



}

object Destinations {
    const val NEWS_SCREEN = "NEWS_SCREEN"
    const val DETAILS_SCREEN = "DETAILS_SCREEN"
    const val FAVOURITE_SCREEN = "FAVOURITE_SCREEN"
    const val PROFILE_SCREEN = "PROFILE_SCREEN"
}






