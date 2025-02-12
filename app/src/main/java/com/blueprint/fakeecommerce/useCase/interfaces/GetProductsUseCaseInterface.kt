package com.blueprint.fakeecommerce.useCase.interfaces

import com.blueprint.fakeecommerce.model.Product

interface GetProductsUseCaseInterface {
    suspend fun fetchProducts(): List<Product>
    suspend fun fetchProductsByCategory(category: String): List<Product>
}