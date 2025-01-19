package com.blueprint.fakeecommerce.store.actions

import com.blueprint.fakeecommerce.model.Product

sealed interface ProductsCartAction {
    data class AddProduct(var product: Product) : ProductsCartAction
    data class RemoveProduct(var product: Product) : ProductsCartAction
}