package com.nikhil.newsAppCompose.presentation.details_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.nikhil.newsAppCompose.common.Constants.DUMMY_IMAGE
import com.nikhil.newsAppCompose.presentation.composable.AppBar
import com.nikhil.newsAppCompose.presentation.ui.theme.NewsAppComposeTheme

@Composable
fun DetailsScreen(
    navController: NavController?,
    title: String = "Title",
    urlToImage: String = DUMMY_IMAGE,
    description: String = "The S&P 500 index surged by 28.7% in 2021, capping " +
            "a three-year gain of 100.4%. Click here for a detailed review..."
) {
    Scaffold(
        topBar = {
            AppBar(title = "Detail Screen", imageVector = Icons.Default.ArrowBack) {
                navController?.navigateUp()
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Image(

                painter = rememberImagePainter(urlToImage),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(maxHeight = 200.dp)
                    .clip(RoundedCornerShape(5))
            )
            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(top = 4.dp)
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    NewsAppComposeTheme {
        DetailsScreen(
            navController = null,
            title = "Title",
            urlToImage = DUMMY_IMAGE,
            description = "The S&P 500 index surged by 28.7% in 2021, capping " +
                    "a three-year gain of 100.4%. Click here for a detailed review..."

            )
    }
}