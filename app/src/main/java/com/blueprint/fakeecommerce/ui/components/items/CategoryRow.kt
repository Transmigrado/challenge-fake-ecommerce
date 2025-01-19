package com.blueprint.fakeecommerce.ui.components.items

import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CategoryRow(
    selectedCategory: String?,
    title: String,
    isAllCategory: Boolean = false,
    onClick: () -> Unit){
    NavigationDrawerItem(
        label = { Text(title) },
        selected = selectedCategory == title || (selectedCategory == null && isAllCategory)  ,
        onClick = onClick
    )
}