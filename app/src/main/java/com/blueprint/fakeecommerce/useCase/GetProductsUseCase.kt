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
    override suspend fun fetchProducts(): List<Product> =
        suspendCoroutine { cont ->
            val call = apiService.getProducts()

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
}