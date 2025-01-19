package com.blueprint.fakeecommerce.store.reducers

import org.reduxkotlin.Reducer
import org.reduxkotlin.typedReducer

data class AppState(
    val productsState: ProductsState = ProductsState(),
    val productsCartState: ProductsCartState = ProductsCartState(),
    val categoryState: CategoryState = CategoryState()
)

val RootReducer: Reducer<AppState> = typedReducer<AppState, Any> { state, action ->
    AppState(
        productsState = ProductsReducer(state.productsState, action),
        productsCartState = ProductsCartReducer(state.productsCartState, action),
        categoryState = CategoriesReducer(state.categoryState, action)
    )
}