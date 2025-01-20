package com.blueprint.fakeecommerce.store.reducers

import com.blueprint.fakeecommerce.model.Product
import com.blueprint.fakeecommerce.model.Rating
import com.blueprint.fakeecommerce.store.actions.ProductsCartAction
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ProductsCartReducerTest {

    private val product = Product(
        id = 1,
        title = "Product 1",
        price = 100f,
        description = "Description",
        category = "Category", image = "image_url", rating = Rating(5.0f, 5)
    )

    @Test
    fun `should add product to cart when AddProduct action is dispatched`() {


        val initialState = ProductsCartState()
        val action = ProductsCartAction.AddProduct(product)


        val newState = ProductsCartReducer(initialState, action)


        assertEquals(1, newState.productsInCart.size)
        assertEquals(1, newState.productsInCart[product.id])
        assertTrue(newState.list.contains(product))
    }

    @Test
    fun `should increase quantity of product when AddProduct action is dispatched twice`() {
        val initialState = ProductsCartState(productsInCart = hashMapOf(product.id to 1), list = listOf(product))
        val action = ProductsCartAction.AddProduct(product)


        val newState = ProductsCartReducer(initialState, action)


        assertEquals(1, newState.productsInCart.size)
        assertEquals(2, newState.productsInCart[product.id]) // Quantity should increase
    }

    @Test
    fun `should not add duplicate product to list when AddProduct action is dispatched again`() {
        val initialState = ProductsCartState(list = listOf(product))
        val action = ProductsCartAction.AddProduct(product)

        val newState = ProductsCartReducer(initialState, action)

        assertEquals(1, newState.list.size)
    }

    @Test
    fun `should remove product from cart when RemoveProduct action is dispatched`() {

        val initialState = ProductsCartState(productsInCart = hashMapOf(product.id to 1), list = listOf(product))
        val action = ProductsCartAction.RemoveProduct(product)

        val newState = ProductsCartReducer(initialState, action)

        assertTrue(newState.productsInCart.isEmpty())
        assertEquals(1, newState.list.size)
    }

    @Test
    fun `should decrease quantity of product when RemoveProduct action is dispatched and quantity is greater than 1`() {

        val initialState = ProductsCartState(productsInCart = hashMapOf(product.id to 2), list = listOf(product))
        val action = ProductsCartAction.RemoveProduct(product)

        val newState = ProductsCartReducer(initialState, action)

        assertEquals(1, newState.productsInCart[product.id])
    }

    @Test
    fun `should remove product completely from cart when RemoveProduct action is dispatched and quantity is 1`() {

        val initialState = ProductsCartState(productsInCart = hashMapOf(product.id to 1), list = listOf(product))
        val action = ProductsCartAction.RemoveProduct(product)

        val newState = ProductsCartReducer(initialState, action)

        assertTrue(newState.productsInCart.isEmpty())
        assertTrue(newState.list.contains(product))
    }


}