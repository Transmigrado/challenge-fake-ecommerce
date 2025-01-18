package com.blueprint.fakeecommerce.store.actions

sealed interface ProductsAction {
    data object Fetch: ProductsAction
    data object FetchError: ProductsAction
    data object FetchSuccess : ProductsAction
}