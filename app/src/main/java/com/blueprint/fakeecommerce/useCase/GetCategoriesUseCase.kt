package com.blueprint.fakeecommerce.useCase

import com.blueprint.fakeecommerce.network.ApiService
import com.blueprint.fakeecommerce.useCase.interfaces.GetCategoriesUseCaseInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class GetCategoriesUseCase(private val apiService: ApiService): GetCategoriesUseCaseInterface {
    override suspend fun fetchCategories(): List<String> =
        suspendCoroutine { cont ->
            val call = apiService.getCategories()

            call.enqueue(object : Callback<MutableList<String>> {
                override fun onResponse(
                    call: Call<MutableList<String>>,
                    response: Response<MutableList<String>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        cont.resume(response.body()!!.toList())
                    } else {
                        cont.resumeWithException(Exception("Error response"))
                    }
                }

                override fun onFailure(call: Call<MutableList<String>>, t: Throwable) {
                    cont.resumeWithException(t)
                }
            })
        }
}