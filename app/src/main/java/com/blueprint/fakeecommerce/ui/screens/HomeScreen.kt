package com.blueprint.fakeecommerce.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.blueprint.fakeecommerce.di.ProductsThunkEntryPoint
import com.blueprint.fakeecommerce.model.Product
import com.blueprint.fakeecommerce.store.reducers.AppState
import com.blueprint.fakeecommerce.ui.components.ProductGrid
import dagger.hilt.android.EntryPointAccessors
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.compose.selectState

@Composable
fun HomeScreen() {

    val context = LocalContext.current
    val thunk = remember {
        EntryPointAccessors.fromApplication(context, ProductsThunkEntryPoint::class.java).getProductsThunk()
    }

    val products by selectState<AppState, List<Product>> { productsState.list }

    val dispatch = rememberDispatcher()

    LaunchedEffect(Unit) {
        dispatch(thunk.getProducts())
    }

    ProductGrid(products)
}

