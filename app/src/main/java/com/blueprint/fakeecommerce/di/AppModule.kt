package com.blueprint.fakeecommerce.di

import com.blueprint.fakeecommerce.network.ApiClient.client
import com.blueprint.fakeecommerce.network.ApiService
import com.blueprint.fakeecommerce.thunk.ProductsThunk
import com.blueprint.fakeecommerce.useCase.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideApiService(): ApiService {
        return client!!.create(ApiService::class.java)
    }

    @Provides
    fun provideGetProductsUseCase(apiService: ApiService): GetProductsUseCase {
        return GetProductsUseCase(apiService)
    }

    @Provides
    fun provideProductsThunk(useCase: GetProductsUseCase): ProductsThunk {
        return ProductsThunk(useCase)
    }
}