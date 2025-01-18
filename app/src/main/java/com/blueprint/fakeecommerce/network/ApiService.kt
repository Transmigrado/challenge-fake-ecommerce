package com.blueprint.fakeecommerce.network

import com.blueprint.fakeecommerce.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    fun getProducts(): Call<MutableList<Product>>
}