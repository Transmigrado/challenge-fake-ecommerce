package com.blueprint.fakeecommerce.ui.components.container

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.blueprint.fakeecommerce.model.Product
import com.blueprint.fakeecommerce.store.actions.ProductsCartAction
import com.blueprint.fakeecommerce.store.reducers.AppState
import com.blueprint.fakeecommerce.ui.components.CartButton
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.compose.selectState

@Composable
fun CartButtonContainer(product: Product) {

    val dispatch = rememberDispatcher()
    val productsInCart by selectState<AppState, HashMap<Int, Int>> { productsCartState.productsInCart }
    val countInCart = productsInCart[product.id] ?: 0


    CartButton(countInCart, onIncrement = {
        dispatch(ProductsCartAction.AddProduct(product))
    }, onDecrement = {
        dispatch(ProductsCartAction.RemoveProduct(product))
    })
}