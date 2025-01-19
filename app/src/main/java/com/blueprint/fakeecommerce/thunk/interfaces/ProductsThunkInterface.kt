package com.blueprint.fakeecommerce.thunk.interfaces

import com.blueprint.fakeecommerce.store.reducers.AppState
import org.reduxkotlin.thunk.Thunk

interface ProductsThunkInterface {
    fun getProducts(): Thunk<AppState>
    fun getProductsByCategory(category: String): Thunk<AppState>
}