package com.blueprint.fakeecommerce.useCase

import com.blueprint.fakeecommerce.model.Category
import com.blueprint.fakeecommerce.network.ApiService
import com.blueprint.fakeecommerce.useCase.interfaces.GetCategoriesUseCaseInterface
import kotlin.coroutines.suspendCoroutine

class GetCategoriesUseCase(private val apiService: ApiService): GetCategoriesUseCaseInterface {
    override suspend fun fetchCategories(): List<Category> =
        suspendCoroutine { cont ->
            val call = apiService.getCategories()

        }

}