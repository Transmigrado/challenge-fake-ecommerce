package com.blueprint.fakeecommerce.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.blueprint.fakeecommerce.ui.components.screens.MainDrawer
import com.blueprint.fakeecommerce.ui.components.container.ProductGridContainer

@Composable
fun HomeScreen(navController: NavHostController) {
    MainDrawer(navController){ innerPadding ->
        ProductGridContainer(innerPadding)
    }
}

