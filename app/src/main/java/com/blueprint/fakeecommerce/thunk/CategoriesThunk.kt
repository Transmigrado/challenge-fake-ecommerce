package com.blueprint.fakeecommerce.thunk

import com.blueprint.fakeecommerce.store.actions.CategoryAction
import com.blueprint.fakeecommerce.store.reducers.AppState
import com.blueprint.fakeecommerce.thunk.interfaces.ProductsThunkInterface
import com.blueprint.fakeecommerce.useCase.interfaces.GetCategoriesUseCaseInterface
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.reduxkotlin.thunk.Thunk

class CategoriesThunk(
    private val useCase: GetCategoriesUseCaseInterface
): ProductsThunkInterface {

    @OptIn(DelicateCoroutinesApi::class)
    override fun getProducts(): Thunk<AppState> = { dispatch, _, _ ->

        GlobalScope.launch {

            val categories = useCase.fetchCategories()
            dispatch(CategoryAction.FetchSuccess(categories))

        }
    }
}