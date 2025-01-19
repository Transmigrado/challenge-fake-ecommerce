package com.blueprint.fakeecommerce.useCase.interfaces


interface GetCategoriesUseCaseInterface {
    suspend fun fetchCategories(): List<String>
}