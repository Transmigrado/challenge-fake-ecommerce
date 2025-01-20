package com.blueprint.fakeecommerce.utils

import com.blueprint.fakeecommerce.model.Product

fun getTotalPrice(products: List<Product>, productsInCart: HashMap<Int, Int>): Double {
    return products.sumOf { product ->
        val quantity = productsInCart[product.id] ?: 0
        product.price.toDouble() * quantity
    }
}