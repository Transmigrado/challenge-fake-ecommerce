package com.blueprint.fakeecommerce.ui.components.groups

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.blueprint.fakeecommerce.model.Product
import com.blueprint.fakeecommerce.model.Rating

import com.blueprint.fakeecommerce.ui.components.items.ProductRow

@Composable
fun ProductList(products: List<Product>, isLoading: Boolean = false) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp)
    ) {
        if(isLoading) {
            items(8) {
                val product = Product(
                    id = 0,
                    title = "Lorem Ipsum Dolor",
                    description = "",
                    price = 0.0f,
                    category = "",
                    image = "",
                    rating = Rating(0.0f, 0)
                )
                ProductRow(product = product, isLoading = true)
            }
        } else {
            items(products.size) { index ->
                val product = products[index]
                ProductRow(product = product)
            }
        }
    }
}