package com.blueprint.fakeecommerce.store.reducers

import com.blueprint.fakeecommerce.store.actions.ProductsAction
import org.reduxkotlin.Reducer
import org.reduxkotlin.typedReducer

data class ProductsState(
    val isLoading: Boolean = false
)

val ProductsReducer: Reducer<ProductsState> = typedReducer<ProductsState, ProductsAction> { state, action ->
    when (action) {
        is ProductsAction.Fetch -> state.copy(isLoading = true)
        is ProductsAction.FetchError -> state.copy(isLoading = false)
        is ProductsAction.FetchSuccess -> state.copy(isLoading = false)
    }
}