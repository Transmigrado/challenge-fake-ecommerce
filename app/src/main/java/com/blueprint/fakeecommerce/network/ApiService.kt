package com.blueprint.fakeecommerce.network

import com.blueprint.fakeecommerce.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    fun getProducts(): Call<MutableList<Product>>

    @GET("products/categories")
    fun getCategories(): Call<MutableList<String>>

    @GET("products/category/{category}")
    fun getProductsByCategory(@Path("category") category: String): Call<MutableList<Product>>

}