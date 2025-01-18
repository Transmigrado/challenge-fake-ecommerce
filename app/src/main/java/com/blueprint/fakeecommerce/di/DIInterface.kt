package com.blueprint.fakeecommerce.di

import com.blueprint.fakeecommerce.thunk.ProductsThunk
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface ProductsThunkEntryPoint {
    fun getProductsThunk(): ProductsThunk
}
