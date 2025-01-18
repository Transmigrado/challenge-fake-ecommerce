package com.blueprint.fakeecommerce.model

data class Product(
    var id: Int,
    var title: String,
    var price: Float,
    var description: String,
    var category: String,
    var image: String,
    var rating: Rating
)

