package com.blueprint.fakeecommerce.store.reducers

import com.blueprint.fakeecommerce.model.Product
import com.blueprint.fakeecommerce.model.Rating
import com.blueprint.fakeecommerce.store.actions.ProductsAction
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ProductsReducerTest {

    @Test
    fun `given Fetch action, it should set isLoading to true`() {

        val initialState = ProductsState(isLoading = false, list = emptyList())
        val action = ProductsAction.Fetch

        val newState = ProductsReducer(initialState, action)

        assertTrue("isLoading should be true after Fetch action", newState.isLoading)
        assertEquals("Product list should be empty after Fetch action", emptyList<Product>(), newState.list)
    }

    @Test
    fun `given FetchError action, it should set isLoading to false`() {

        val initialState = ProductsState(isLoading = true, list = emptyList())
        val action = ProductsAction.FetchError


        val newState = ProductsReducer(initialState, action)


        assertFalse("isLoading should be false after FetchError action", newState.isLoading)
        assertEquals("Product list should be empty after FetchError action", emptyList<Product>(), newState.list)
    }

    @Test
    fun `given FetchSuccess action, it should update the list of products`() {
        val initialState = ProductsState(isLoading = true, list = emptyList())
        val products = listOf(
            Product(
                id = 1,
                title = "Product 1",
                price = 10.0F,
                description = "A test product",
                category = "category 1",
                image = "https://fakeImage.com",
                rating = Rating(0.0F, 0)
                ),
            Product(
                id = 2,
                title = "Product 2",
                price = 20.0F,
                description = "Another test product",
                category = "category 1",
                image = "https://fakeImage.com",
                rating = Rating(0.0F, 0)
                )
        )
        val action = ProductsAction.FetchSuccess(products)

        val newState = ProductsReducer(initialState, action)

        assertFalse("isLoading should be false after FetchSuccess action", newState.isLoading)
        assertEquals("Product list should be updated with FetchSuccess action", products, newState.list)
    }

    @Test
    fun `given multiple actions, it should correctly update the state`() {

        val initialState = ProductsState(isLoading = false, list = emptyList())


        val fetchAction = ProductsAction.Fetch
        val afterFetchState = ProductsReducer(initialState, fetchAction)


        assertTrue("isLoading should be true after Fetch action", afterFetchState.isLoading)
        assertEquals("Product list should still be empty after Fetch action", emptyList<Product>(), afterFetchState.list)


        val products = listOf(
            Product(
                id = 1,
                title = "Product 1",
                price = 10.0F,
                description = "A test product",
                category = "category 1",
                image = "https://fakeImage.com",
                rating = Rating(0.0F, 0)
            ),
            Product(
                id = 2,
                title = "Product 2",
                price = 20.0F,
                description = "Another test product",
                category = "category 1",
                image = "https://fakeImage.com",
                rating = Rating(0.0F, 0)
            )
        )
        val fetchSuccessAction = ProductsAction.FetchSuccess(products)
        val afterFetchSuccessState = ProductsReducer(afterFetchState, fetchSuccessAction)

        assertFalse("isLoading should be false after FetchSuccess action", afterFetchSuccessState.isLoading)
        assertEquals("Product list should be updated with FetchSuccess action", products, afterFetchSuccessState.list)

        val fetchErrorAction = ProductsAction.FetchError
        val afterFetchErrorState = ProductsReducer(afterFetchSuccessState, fetchErrorAction)

        assertFalse("isLoading should be false after FetchError action", afterFetchErrorState.isLoading)
        assertEquals("Product list should remain unchanged after FetchError action", products, afterFetchErrorState.list)
    }
}