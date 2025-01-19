package com.blueprint.fakeecommerce.ui.components.container

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.blueprint.fakeecommerce.di.ProductsThunkEntryPoint
import com.blueprint.fakeecommerce.model.Product
import com.blueprint.fakeecommerce.store.reducers.AppState
import com.blueprint.fakeecommerce.ui.components.ProductGrid
import dagger.hilt.android.EntryPointAccessors
import org.reduxkotlin.compose.selectState

@Composable
fun ProductListCartContainer(){
    val context = LocalContext.current
    val thunk = remember {
        EntryPointAccessors.fromApplication(context, ProductsThunkEntryPoint::class.java).getProductsThunk()
    }

    val products by selectState<AppState, List<Product>> { productsState.list }
    val productsInCart by selectState<AppState, HashMap<Int, Int>> { productsCartState.productsInCart }
    val isLoading by selectState<AppState, Boolean> { productsState.isLoading }

    val filteredProducts = products.filter { product ->
        val quantityInCart = productsInCart[product.id] ?: 0
        quantityInCart > 0
    }



    Box{
        ProductGrid(filteredProducts, isLoading)
    }
}