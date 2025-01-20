package com.blueprint.fakeecommerce.ui.screens


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.blueprint.fakeecommerce.ui.components.container.ProductDetailContainer
import com.blueprint.fakeecommerce.ui.components.screens.BasicScreen

@Composable
fun ProductDetailScreen(navController: NavHostController) {
    BasicScreen(navController) {
        ProductDetailContainer()
    }
}