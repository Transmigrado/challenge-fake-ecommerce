package com.blueprint.fakeecommerce.store.actions

import com.blueprint.fakeecommerce.model.Category

sealed interface CategoryAction {
    data object Fetch: CategoryAction
    data object FetchError: CategoryAction
    data class FetchSuccess(var categories: List<Category>) : CategoryAction
}