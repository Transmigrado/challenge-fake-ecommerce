package com.blueprint.fakeecommerce.thunk.interfaces

import com.blueprint.fakeecommerce.store.reducers.AppState
import org.reduxkotlin.thunk.Thunk

interface CategoriesThunkInterface {
    fun getCategories(): Thunk<AppState>
}