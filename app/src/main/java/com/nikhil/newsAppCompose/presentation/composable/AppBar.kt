package com.nikhil.newsAppCompose.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AppBar(title: String, imageVector: ImageVector, action: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            Icon(
                modifier = Modifier.padding(start = 16.dp)
                    .clickable { action.invoke() },
                imageVector = imageVector,
                contentDescription = "icon"
            )
        },
        title = { Text(text = title) }
    )
}