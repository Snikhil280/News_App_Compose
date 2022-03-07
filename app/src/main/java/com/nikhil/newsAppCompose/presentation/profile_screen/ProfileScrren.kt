package com.nikhil.newsAppCompose.presentation.profile_screen

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.nikhil.newsAppCompose.presentation.composable.AppBar
import com.nikhil.newsAppCompose.presentation.composable.BottomNavigationBar

@Composable
fun ProfileScreen(
    navController: NavController
){
    Scaffold(
        topBar = {
            AppBar(title = "Profile Screen", imageVector = Icons.Default.ArrowBack) {
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
        Text(text = "Hello")
    }
}