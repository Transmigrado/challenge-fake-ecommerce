package com.blueprint.fakeecommerce.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CategoryList(categories: List<String>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(categories.size) { index ->
            CategoryRow(categories[index])
        }
    }
}