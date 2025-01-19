package com.blueprint.fakeecommerce.useCases

import com.blueprint.fakeecommerce.model.Product
import com.blueprint.fakeecommerce.model.Rating
import com.blueprint.fakeecommerce.network.ApiService
import com.blueprint.fakeecommerce.useCase.GetProductsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import io.mockk.*
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
class GetProductsUseCaseTest {

    private lateinit var apiService: ApiService
    private lateinit var getProductsUseCase: GetProductsUseCase
    private val mockProducts = listOf(
        Product(
            id = 1,
            title = "Product 1",
            price = 10.0f,
            description = "Description",
            category = "Category",
            image = "imageUrl",
            rating = Rating(0.0f, 0)),
        Product(
            id = 2,
            title = "Product 2",
            price = 15.0f,
            description = "Description",
            category = "Category",
            image = "imageUrl",
            rating = Rating(0.0f, 0))
    )


    @Before
    fun setup() {
        apiService = mockk()
        getProductsUseCase = GetProductsUseCase(apiService)
    }

    @Test
    fun `test fetchProducts - successful response`() = runTest {

        val mockCall = mockk<Call<MutableList<Product>>>()

        every { apiService.getProducts() } returns mockCall

        coEvery { mockCall.enqueue(any()) } answers {
            val callback = firstArg<Callback<MutableList<Product>>>()
            callback.onResponse(mockCall, Response.success(mockProducts.toMutableList()))
        }

        val result = getProductsUseCase.fetchProducts()

        assertEquals(mockProducts, result)
    }

    @Test
    fun `test fetchProducts by category - successful response`() = runTest {

        val mockCall = mockk<Call<MutableList<Product>>>()

        every { apiService.getProducts() } returns mockCall

        coEvery { mockCall.enqueue(any()) } answers {
            val callback = firstArg<Callback<MutableList<Product>>>()
            callback.onResponse(mockCall, Response.success(mockProducts.toMutableList()))
        }

        val result = getProductsUseCase.fetchProductsByCategory("clothes")

        assertEquals(mockProducts, result)
    }


}