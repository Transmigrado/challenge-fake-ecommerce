package com.blueprint.fakeecommerce.thunk

import com.blueprint.fakeecommerce.store.actions.CategoryAction
import com.blueprint.fakeecommerce.testUtils.ReduxStore
import com.blueprint.fakeecommerce.useCase.interfaces.GetCategoriesUseCaseInterface
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CategoriesThunkTest {

    private lateinit var useCase: GetCategoriesUseCaseInterface
    private lateinit var dispatch: (Any) -> Unit
    private lateinit var categoriesThunk: CategoriesThunk
    private val store = mockk<ReduxStore>()

    @Before
    fun setUp() {
        useCase = mockk()

        dispatch = mockk(relaxed = true)

        categoriesThunk = CategoriesThunk(useCase)

        every{ store.dispatch } returns mockk()
        every { store.getState } returns mockk()
        every { store.dispatch(any()) } returns Unit
    }

    @Test
    fun `should dispatch FetchSuccess when getCategories is called`() = runTest {

        val fakeCategories = listOf("Electronics", "Clothing", "Toys")
        coEvery { useCase.fetchCategories() } returns fakeCategories

        val thunk = categoriesThunk.getCategories()
        thunk(dispatch, store.getState, null)

        verify {
            dispatch(CategoryAction.FetchSuccess(fakeCategories))
        }

        confirmVerified(dispatch)
    }

    @Test
    fun `should handle empty categories list when getCategories returns empty list`() = runTest {

        coEvery { useCase.fetchCategories() } returns emptyList()


        val thunk = categoriesThunk.getCategories()
        thunk(dispatch, store.getState, null)


        verify {
            dispatch(CategoryAction.FetchSuccess(emptyList()))
        }

        confirmVerified(dispatch)
    }

    @Test
    fun `should dispatch FetchSuccess when categories are fetched successfully`() = runTest {

        val fakeCategories = listOf("Home", "Electronics", "Fashion")
        coEvery { useCase.fetchCategories() } returns fakeCategories


        val thunk = categoriesThunk.getCategories()
        thunk(dispatch, store.getState, null)

        verifyOrder {
            dispatch(CategoryAction.FetchSuccess(fakeCategories))
        }


        confirmVerified(dispatch)
    }

    @Test
    fun `should handle exception and not crash if categories fetching fails`() = runTest {

        coEvery { useCase.fetchCategories() } throws Exception("Error fetching categories")

        val thunk = categoriesThunk.getCategories()
        thunk(dispatch, store.getState, null)


        verify(exactly = 0) {
            dispatch(CategoryAction.FetchSuccess(any()))
        }

        confirmVerified(dispatch)
    }
}