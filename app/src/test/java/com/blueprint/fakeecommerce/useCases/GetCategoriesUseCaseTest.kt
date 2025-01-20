package com.blueprint.fakeecommerce.useCases

import com.blueprint.fakeecommerce.network.ApiService
import com.blueprint.fakeecommerce.useCase.GetCategoriesUseCase
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

@ExperimentalCoroutinesApi
class GetCategoriesUseCaseTest {
    private lateinit var apiService: ApiService
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase

    @Before
    fun setUp() {
        apiService = mockk()
        getCategoriesUseCase = GetCategoriesUseCase(apiService)
    }

    @Test
    fun `should return categories when fetchCategories is successful`() = runTest {

        val fakeCategories = listOf("Electronics", "Clothing", "Toys")

        val call = mockk<Call<MutableList<String>>>()
        val response = Response.success(fakeCategories.toMutableList())
        every { apiService.getCategories() } returns call
        every { call.enqueue(any<Callback<MutableList<String>>>()) } answers {
            val callback = firstArg<Callback<MutableList<String>>>()
            callback.onResponse(call, response)
        }

        val result = getCategoriesUseCase.fetchCategories()

        assertEquals(fakeCategories, result)


    }

    @Test
    fun `should return empty list when fetchCategories fails`() = runTest {

        val call = mockk<Call<MutableList<String>>>()
        every { apiService.getCategories() } returns call
        every { call.enqueue(any<Callback<MutableList<String>>>()) } answers {
            val callback = firstArg<Callback<MutableList<String>>>()
            callback.onFailure(call, Throwable("Network Error"))
        }

        val result = getCategoriesUseCase.fetchCategories()

        assertEquals(emptyList<String>(), result)


    }

    @Test
    fun `should return empty list when fetchCategories returns unsuccessful response`() = runTest {

        val call = mockk<Call<MutableList<String>>>()
        val response: Response<MutableList<String>> = Response.error(500, ResponseBody.create(null, ""))
        every { apiService.getCategories() } returns call
        every { call.enqueue(any<Callback<MutableList<String>>>()) } answers {
            val callback = firstArg<Callback<MutableList<String>>>()
            callback.onResponse(call, response)
        }


        val result = getCategoriesUseCase.fetchCategories()


        assertEquals(emptyList<String>(), result)


    }
}