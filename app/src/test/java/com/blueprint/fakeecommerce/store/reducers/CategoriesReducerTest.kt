package com.blueprint.fakeecommerce.store.reducers

import com.blueprint.fakeecommerce.store.actions.CategoryAction
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CategoriesReducerTest {

    @Test
    fun `should set isLoading to true when Fetch action is dispatched`() {

        val initialState = CategoryState(isLoading = false)
        val action = CategoryAction.Fetch

        val newState = CategoriesReducer(initialState, action)

        assertTrue(newState.isLoading)
        assertNull(newState.selectedCategory)
        assertTrue(newState.list.isEmpty())
    }

    @Test
    fun `should set isLoading to false when FetchError action is dispatched`() {

        val initialState = CategoryState(isLoading = true) // already loading
        val action = CategoryAction.FetchError

        val newState = CategoriesReducer(initialState, action)

        assertFalse(newState.isLoading)
        assertNull(newState.selectedCategory)
        assertTrue(newState.list.isEmpty())
    }

    @Test
    fun `should update list and set isLoading to false when FetchSuccess action is dispatched`() {

        val initialState = CategoryState(isLoading = true)
        val categories = listOf("Electronics", "Clothing", "Home")
        val action = CategoryAction.FetchSuccess(categories)


        val newState = CategoriesReducer(initialState, action)

        assertFalse(newState.isLoading)
        assertEquals(categories, newState.list)
        assertNull(newState.selectedCategory)
    }

    @Test
    fun `should update selectedCategory when SelectCategory action is dispatched`() {

        val initialState = CategoryState(selectedCategory = null)
        val category = "Electronics"
        val action = CategoryAction.SelectCategory(category)


        val newState = CategoriesReducer(initialState, action)


        assertEquals(category, newState.selectedCategory)
        assertFalse(newState.isLoading)
        assertTrue(newState.list.isEmpty())
    }


}