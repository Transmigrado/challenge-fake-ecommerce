package com.blueprint.fakeecommerce.thunk

import com.blueprint.fakeecommerce.thunk.interfaces.ProductsThunkInterface
import com.blueprint.fakeecommerce.useCase.interfaces.GetProductsUseCaseInterface


class ProductsThunk(val useCase: GetProductsUseCaseInterface): ProductsThunkInterface {
    override fun getProducts() {

    }
}