package com.blueprint.fakeecommerce.ui.screens

import androidx.compose.runtime.Composable
import com.blueprint.fakeecommerce.ui.components.MainDrawer
import com.blueprint.fakeecommerce.ui.components.container.ProductGridContainer

@Composable
fun HomeScreen() {
    MainDrawer{
        ProductGridContainer()
    }
}

