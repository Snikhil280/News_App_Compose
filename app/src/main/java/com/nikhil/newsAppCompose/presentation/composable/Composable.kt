package com.nikhil.newsAppCompose.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.nikhil.newsAppCompose.domain.model.Article
import com.nikhil.newsAppCompose.presentation.Destinations
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NewsRows(
    article: Article,
    onItemClick: () -> Unit,
    actionText: String? = null,
    actionIcon: ImageVector? = null,
    actionOnClick: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .wrapContentHeight()
            .clickable { onItemClick.invoke() },
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp,

        ) {
        Column {
            Image(

                painter = rememberImagePainter(article.urlToImage?: ""),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(maxHeight = 200.dp)
            )
            Text(
                text = article.title?:"",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
            )
            if (actionText != null && actionIcon != null && actionOnClick != null){
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable { actionOnClick.invoke() }
                ){
                    Icon(
                        imageVector = actionIcon,
                        contentDescription = actionText,
                    )
                    Text(
                        text = actionText,
                        modifier = Modifier
                            .padding(start = 16.dp),
                    )

                }

            }
        }
    }

}

@Composable
fun LazyNewsColumn(
    articles: List<Article>,
    onItemClick: (article: Article) -> Unit,
    actionText: String? = null,
    actionIcon: ImageVector? = null,
    actionOnClick: ((article: Article) -> Unit)? =null
){
    LazyColumn(
        modifier = Modifier.padding(bottom = 56.dp)
    ) {
        items(articles) { article ->
            NewsRows(
                article = article,
                onItemClick = {onItemClick.invoke(article)},
                actionText = actionText,
                actionIcon = actionIcon,
                actionOnClick = {
                    actionOnClick?.invoke(article)
                }
            )
        }
    }
}

@Composable
fun CircularProgress() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorMessage(errorMessage: String, onClick: (()-> Unit)? = null) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = errorMessage)
        onClick?.let {
            Button(
                onClick = { it.invoke() },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = "Retry...",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

    }
}