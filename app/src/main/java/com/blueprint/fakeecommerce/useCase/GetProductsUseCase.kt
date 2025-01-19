package com.blueprint.fakeecommerce.useCase


import com.blueprint.fakeecommerce.model.Product
import com.blueprint.fakeecommerce.network.ApiService
import com.blueprint.fakeecommerce.useCase.interfaces.GetProductsUseCaseInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class GetProductsUseCase(private val apiService: ApiService): GetProductsUseCaseInterface {

    private suspend fun fetchDataFromApi(call: Call<MutableList<Product>>): List<Product> =
        suspendCoroutine { cont ->
            call.enqueue(object : Callback<MutableList<Product>> {
                override fun onResponse(
                    call: Call<MutableList<Product>>,
                    response: Response<MutableList<Product>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        cont.resume(response.body()!!.toList())
                    } else {
                        cont.resumeWithException(Exception("Error response"))
                    }
                }

                override fun onFailure(call: Call<MutableList<Product>>, t: Throwable) {
                    cont.resumeWithException(t)
                }
            })
        }

    override suspend fun fetchProducts(): List<Product> =
        fetchDataFromApi(apiService.getProducts())

    override suspend fun fetchProductsByCategory(category: String): List<Product> =
        fetchDataFromApi(apiService.getProductsByCategory(category))
}