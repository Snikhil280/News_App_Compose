package com.nikhil.newsAppCompose.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nikhil.newsAppCompose.presentation.Destinations

val bottomNavItems = listOf<BottomNavItem>(
    BottomNavItem(
        title = "News",
        route = Destinations.NEWS_SCREEN,
        icon = Icons.Default.List
    ),
    BottomNavItem(
        title = "Favorite",
        route = Destinations.FAVOURITE_SCREEN,
        icon = Icons.Default.Favorite
    ),
    BottomNavItem(
        title = "Favorite",
        route = Destinations.PROFILE_SCREEN,
        icon = Icons.Default.Person
    )
)

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem> = bottomNavItems,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = false,
                onClick = { onItemClick(item) },
                icon = {
                    Column(
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null
                        )
                        if (selected)
                            Text(
                                text = item.title,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                    }
                }
            )
        }
    }
}

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: ImageVector
)