package com.blueprint.fakeecommerce.thunk

import com.blueprint.fakeecommerce.model.Product
import com.blueprint.fakeecommerce.model.Rating
import com.blueprint.fakeecommerce.store.actions.CategoryAction
import com.blueprint.fakeecommerce.store.actions.ProductsAction
import com.blueprint.fakeecommerce.testUtils.ReduxStore
import com.blueprint.fakeecommerce.useCase.interfaces.GetProductsUseCaseInterface
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class ProductsThunkTest {

    private lateinit var useCase: GetProductsUseCaseInterface
    private lateinit var dispatch: (Any) -> Unit
    private lateinit var productsThunk: ProductsThunk
    private val store = mockk<ReduxStore>()

    private val mockProducts = listOf(
        Product(
            id = 1,
            title = "Product 1",
            price = 10.0f,
            description = "Description",
            category = "Category",
            image = "imageUrl",
            rating = Rating(0.0f, 0)
        ),
        Product(
            id = 2,
            title = "Product 2",
            price = 15.0f,
            description = "Description",
            category = "Category",
            image = "imageUrl",
            rating = Rating(0.0f, 0)
        )
    )

    @Before
    fun setUp() {

        useCase = mockk()
        dispatch = mockk(relaxed = true)
        productsThunk = ProductsThunk(useCase)

        every{ store.dispatch } returns mockk()
        every { store.getState } returns mockk()
        every { store.dispatch(any()) } returns Unit
    }

    @Test
    fun `should dispatch Fetch and FetchSuccess on getProducts`() = runTest {


        coEvery { useCase.fetchProducts() } returns mockProducts


        val thunk = productsThunk.getProducts()
        thunk(dispatch, store.getState, null)


        verifyOrder {
            dispatch(ProductsAction.Fetch)
            dispatch(CategoryAction.SelectCategory(null))
            dispatch(ProductsAction.FetchSuccess(mockProducts))
        }


        confirmVerified(dispatch)
    }


    @Test
    fun `should dispatch Fetch and FetchSuccess on getProductsByCategory`() = runTest {

        val fakeCategory = "Electronics"

        coEvery { useCase.fetchProductsByCategory(fakeCategory) } returns mockProducts


        val thunk = productsThunk.getProductsByCategory(fakeCategory)
        thunk(dispatch, store.getState, null)


        verifyOrder {
            dispatch(ProductsAction.Fetch)
            dispatch(CategoryAction.SelectCategory(fakeCategory))
            dispatch(ProductsAction.FetchSuccess(mockProducts))
        }


        confirmVerified(dispatch)
    }

    @Test
    fun `should dispatch Fetch and FetchSuccess with empty list when no products are returned`() = runTest {

        coEvery { useCase.fetchProducts() } returns emptyList()


        val thunk = productsThunk.getProducts()
        thunk(dispatch, store.getState, null)


        verifyOrder {
            dispatch(ProductsAction.Fetch)
            dispatch(CategoryAction.SelectCategory(null))
            dispatch(ProductsAction.FetchSuccess(emptyList()))
        }


        confirmVerified(dispatch)
    }

    @Test
    fun `should dispatch Fetch and FetchSuccess on getProductsByCategory with empty list`() = runTest {

        val fakeCategory = "Toys"
        coEvery { useCase.fetchProductsByCategory(fakeCategory) } returns emptyList()


        val thunk = productsThunk.getProductsByCategory(fakeCategory)
        thunk(dispatch, store.getState, null)


        verifyOrder {
            dispatch(ProductsAction.Fetch)
            dispatch(CategoryAction.SelectCategory(fakeCategory))
            dispatch(ProductsAction.FetchSuccess(emptyList()))
        }


        confirmVerified(dispatch)
    }

}