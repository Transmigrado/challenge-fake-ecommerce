package com.blueprint.fakeecommerce.store.reducers


import com.blueprint.fakeecommerce.model.ProductCart
import com.blueprint.fakeecommerce.store.actions.ProductsCartAction
import org.reduxkotlin.Reducer
import org.reduxkotlin.typedReducer

data class ProductsCartState(
    var list: List<ProductCart> = emptyList()
)

val ProductsCartReducer: Reducer<ProductsCartState> = typedReducer<ProductsCartState, ProductsCartAction> { state, action ->
    when (action) {
        is ProductsCartAction.AddProduct -> state.copy()
    }
}