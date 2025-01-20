package com.blueprint.fakeecommerce.ui.components.info

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Rating(rating: Float){

    Row(
        modifier = Modifier.padding(10.dp)
    ) {

        for (i in 1..5) {
            when {
                rating >= i -> {
                    Icon(Icons.Default.Star, tint = Color.Blue, contentDescription = "Rating $i")
                }
                rating >= i - 0.5 -> {
                    Icon(Icons.Default.Star, tint = Color.Gray, contentDescription = "Rating $i")
                }
                else -> {
                    Icon(Icons.Default.Star, tint = Color.Gray, contentDescription = "Rating $i")
                }
            }
        }
    }
}