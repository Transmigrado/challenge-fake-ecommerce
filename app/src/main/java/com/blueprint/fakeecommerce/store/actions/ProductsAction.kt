package com.blueprint.fakeecommerce.store.actions

import com.blueprint.fakeecommerce.model.Product

sealed interface ProductsAction {
    data object Fetch: ProductsAction
    data object FetchError: ProductsAction
    data class FetchSuccess(var products: List<Product>) : ProductsAction
    data class SelectProduct(var product: Product): ProductsAction
}