package com.blueprint.fakeecommerce.ui.components.items

import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CategoryRow(title: String){
    NavigationDrawerItem(
        label = { Text(title) },
        selected = false,
        onClick = {  }
    )
}