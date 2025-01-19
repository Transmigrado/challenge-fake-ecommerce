package com.blueprint.fakeecommerce.ui.components.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicScreen(
    navController: NavHostController,
    content: @Composable (PaddingValues) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fake Ecommerce") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Menu")
                    }
                }
            )
        }
    ) { innerPadding ->
        content(innerPadding)
    }

}