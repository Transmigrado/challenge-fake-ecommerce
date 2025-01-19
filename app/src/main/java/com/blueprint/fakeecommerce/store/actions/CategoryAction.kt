package com.blueprint.fakeecommerce.store.actions



sealed interface CategoryAction {
    data object Fetch: CategoryAction
    data object FetchError: CategoryAction
    data class FetchSuccess(var categories: List<String>) : CategoryAction
    data class SelectCategory(var category: String?): CategoryAction
}