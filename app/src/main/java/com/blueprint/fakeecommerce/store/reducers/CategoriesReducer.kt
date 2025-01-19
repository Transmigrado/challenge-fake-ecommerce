package com.blueprint.fakeecommerce.store.reducers

import com.blueprint.fakeecommerce.store.actions.CategoryAction
import org.reduxkotlin.Reducer
import org.reduxkotlin.typedReducer

data class CategoryState(
    val isLoading: Boolean = false,
    val selectedCategory: String? = null,
    var list: List<String> = emptyList()
)

val CategoriesReducer: Reducer<CategoryState> = typedReducer<CategoryState, CategoryAction> { state, action ->
    when (action) {
        is CategoryAction.Fetch -> state.copy(isLoading = true)
        is CategoryAction.FetchError -> state.copy(isLoading = false)
        is CategoryAction.FetchSuccess -> state.copy(isLoading = false, list = action.categories)
    }
}