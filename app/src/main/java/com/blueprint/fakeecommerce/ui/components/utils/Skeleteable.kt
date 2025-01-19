package com.blueprint.fakeecommerce.ui.components.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.blueprint.fakeecommerce.ui.components.modifiers.shimmerBackground

@Composable
fun Skeleteable(
    isLoading: Boolean = false,
    content: @Composable (Modifier) -> Unit

) {

    val modifier = Modifier

    val updatedModifier = if(isLoading) {
        modifier
            .shimmerBackground(RoundedCornerShape(4.dp))
    } else {
        modifier
    }

    Box(
        modifier = updatedModifier
    ){
        if(isLoading) {
            content(Modifier.alpha(0.0f))
        } else {
            content(Modifier)
        }
    }

}