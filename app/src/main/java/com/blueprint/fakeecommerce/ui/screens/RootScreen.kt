package com.blueprint.fakeecommerce.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun RootScreen() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("productDetail") { ProductDetailScreen() }
        composable("shoppingCartDetail") { ShoppingCartDetailScreen() }
    }

}

