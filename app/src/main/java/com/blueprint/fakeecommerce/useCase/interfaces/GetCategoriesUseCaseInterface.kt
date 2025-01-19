package com.blueprint.fakeecommerce.useCase.interfaces

import com.blueprint.fakeecommerce.model.Category

interface GetCategoriesUseCaseInterface {
    suspend fun fetchCategories(): List<Category>
}