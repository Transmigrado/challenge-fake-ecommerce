package com.blueprint.fakeecommerce.ui.components.container

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.blueprint.fakeecommerce.model.Product
import com.blueprint.fakeecommerce.store.reducers.AppState
import com.blueprint.fakeecommerce.ui.components.groups.ProductList

import org.reduxkotlin.compose.selectState

@Composable
fun ProductListCartContainer(){

    val products by selectState<AppState, List<Product>> { productsState.list }
    val productsInCart by selectState<AppState, HashMap<Int, Int>> { productsCartState.productsInCart }
    val isLoading by selectState<AppState, Boolean> { productsState.isLoading }

    val filteredProducts = products.filter { product ->
        val quantityInCart = productsInCart[product.id] ?: 0
        quantityInCart > 0
    }

    ProductList(filteredProducts, isLoading)
}