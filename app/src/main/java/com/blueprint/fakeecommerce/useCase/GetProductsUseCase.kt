package com.blueprint.fakeecommerce.useCase


import com.blueprint.fakeecommerce.model.Product
import com.blueprint.fakeecommerce.network.ApiService
import com.blueprint.fakeecommerce.useCase.interfaces.GetProductsUseCaseInterface
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class GetProductsUseCase(private val apiService: ApiService): GetProductsUseCaseInterface {
    override suspend fun fetchProducts(): List<Product> =
        suspendCoroutine { cont ->
            val call = apiService.getProducts()
            cont.resume(emptyList())
        }


}