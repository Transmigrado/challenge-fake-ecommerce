package com.blueprint.fakeecommerce.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.blueprint.fakeecommerce.ui.components.screens.BasicScreen
import com.blueprint.fakeecommerce.ui.components.container.ProductListCartContainer

@Composable
fun ShoppingCartDetailScreen(navController: NavHostController) {
    BasicScreen(navController) {
        ProductListCartContainer()
    }
}

