package com.blueprint.fakeecommerce.thunk

import android.util.Log
import com.blueprint.fakeecommerce.store.actions.ProductsAction
import com.blueprint.fakeecommerce.store.reducers.AppState
import com.blueprint.fakeecommerce.thunk.interfaces.ProductsThunkInterface
import com.blueprint.fakeecommerce.useCase.interfaces.GetProductsUseCaseInterface
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.reduxkotlin.thunk.Thunk


class ProductsThunk(
    private val useCase: GetProductsUseCaseInterface
): ProductsThunkInterface {

    @OptIn(DelicateCoroutinesApi::class)
    override fun getProducts(): Thunk<AppState> = { dispatch, getState, store ->
        GlobalScope.launch {

            val products = useCase.fetchProducts()
            dispatch(ProductsAction.FetchSuccess(products))
            Log.d("TAG", products.toString())
        }
    }
}