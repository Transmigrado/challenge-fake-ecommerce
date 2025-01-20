package com.blueprint.fakeecommerce.utils

import com.blueprint.fakeecommerce.model.Product
import com.blueprint.fakeecommerce.model.Rating
import org.junit.Test
import org.testng.Assert.assertEquals

class PriceTest {

    @Test
    fun `should return correct total price when there are products in the cart`() {

        val product1 = Product(
            id = 1,
            title = "Product 1",
            price = 10.0f,
            description = "",
            category = "",
            image = "",
            rating = Rating(0.0f, 0)
            )

        val product2 = Product(
            id = 2,
            title = "Product 1",
            price = 20.0f,
            description = "",
            category = "",
            image = "",
            rating = Rating(0.0f, 0)
        )

        val product3 = Product(
            id = 3,
            title = "Product 1",
            price = 30.0f,
            description = "",
            category = "",
            image = "",
            rating = Rating(0.0f, 0)
        )


        val productsInCart = hashMapOf(
            1 to 2,
            2 to 1,
            3 to 3
        )

        val products = listOf(product1, product2, product3)

        val totalPrice = getTotalPrice(products, productsInCart)

        val expectedTotal = (10.0 * 2) + (20.0 * 1) + (30.0 * 3)
        assertEquals(expectedTotal, totalPrice, "El precio total es incorrecto.")
    }

    @Test
    fun `should return 0 when the cart is empty`() {

        val products = listOf(
            Product(
                id = 1,
                title = "Product 1",
                price = 10.0f,
                description = "",
                category = "",
                image = "",
                rating = Rating(0.0f, 0)
            ),
            Product(
                id = 2,
                title = "Product 1",
                price = 20.0f,
                description = "",
                category = "",
                image = "",
                rating = Rating(0.0f, 0)
            )
        )

        val productsInCart = hashMapOf<Int, Int>()

        val totalPrice = getTotalPrice(products, productsInCart)

        assertEquals(0.0, totalPrice, "total price should be 0")
    }

    @Test
    fun `should return correct total price when some products have quantity 0`() {

        val product1 = Product(
            id = 1,
            title = "Product 1",
            price = 10.0f,
            description = "",
            category = "",
            image = "",
            rating = Rating(0.0f, 0)
        )
        val product2 = Product(
            id = 2,
            title = "Product 1",
            price = 20.0f,
            description = "",
            category = "",
            image = "",
            rating = Rating(0.0f, 0)
        )

        val product3 = Product(
            id = 3,
            title = "Product 1",
            price = 30.0f,
            description = "",
            category = "",
            image = "",
            rating = Rating(0.0f, 0)
        )

        val productsInCart = hashMapOf(
            1 to 2,
            2 to 0,
            3 to 3
        )

        val products = listOf(product1, product2, product3)

        val totalPrice = getTotalPrice(products, productsInCart)

        val expectedTotal = (10.0 * 2) + (20.0 * 0) + (30.0 * 3)
        assertEquals(expectedTotal, totalPrice, "total price is incorrect when somethings products have value 0")
    }

    @Test
    fun `should return correct total price when product is not in cart`() {

        val product1 = Product(
            id = 1,
            title = "Product 1",
            price = 10.0f,
            description = "",
            category = "",
            image = "",
            rating = Rating(0.0f, 0)
        )
        val product2 = Product(
            id = 2,
            title = "Product 1",
            price = 20.0f,
            description = "",
            category = "",
            image = "",
            rating = Rating(0.0f, 0)
        )

        val productsInCart = hashMapOf(
            1 to 2
        )

        val products = listOf(product1, product2)

        val totalPrice = getTotalPrice(products, productsInCart)

        val expectedTotal = (10.0 * 2) // Solo el precio de Product 1
        assertEquals(expectedTotal, totalPrice, "El precio total no es correcto cuando un producto no está en el carrito.")
    }

    @Test
    fun `should return 0 when all product quantities are 0`() {
        val product1 = Product(
            id = 1,
            title = "Product 1",
            price = 10.0f,
            description = "",
            category = "",
            image = "",
            rating = Rating(0.0f, 0)
        )
        val product2 = Product(
            id = 2,
            title = "Product 1",
            price = 20.0f,
            description = "",
            category = "",
            image = "",
            rating = Rating(0.0f, 0)
        )


        val productsInCart = hashMapOf(
            1 to 0,
            2 to 0
        )


        val products = listOf(product1, product2)


        val totalPrice = getTotalPrice(products, productsInCart)


        assertEquals(0.0, totalPrice, "El precio total debería ser 0 cuando todos los productos tienen cantidad 0.")
    }
}