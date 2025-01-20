package com.blueprint.fakeecommerce.store.reducers

import com.blueprint.fakeecommerce.model.Product
import com.blueprint.fakeecommerce.store.actions.ProductsCartAction
import org.reduxkotlin.Reducer
import org.reduxkotlin.typedReducer

data class ProductsCartState(
    val productsInCart: HashMap<Int, Int> = HashMap(),
    var list: List<Product> = emptyList()
)

val ProductsCartReducer: Reducer<ProductsCartState> = typedReducer<ProductsCartState, ProductsCartAction> { state, action ->
    when (action) {
        is ProductsCartAction.AddProduct -> {

            val updatedProductsInCart = state.productsInCart.toMutableMap()

            if (updatedProductsInCart.containsKey(action.product.id)) {
                updatedProductsInCart[action.product.id] = updatedProductsInCart[action.product.id]!! + 1
            } else {
                updatedProductsInCart[action.product.id] = 1
            }
            val updatedList = if (state.list.none { it.id == action.product.id }) {
                state.list + action.product
            } else {
                state.list
            }


            state.copy(
                productsInCart = HashMap(updatedProductsInCart),
                list = updatedList
                )
        }
        is ProductsCartAction.RemoveProduct -> {
            val updatedProductsInCart = state.productsInCart.toMutableMap()

            if (updatedProductsInCart.containsKey(action.product.id)) {
                val currentQuantity = updatedProductsInCart[action.product.id]!!

                if (currentQuantity > 1) {
                    updatedProductsInCart[action.product.id] = currentQuantity - 1
                } else {
                    updatedProductsInCart.remove(action.product.id)
                }
            }

            state.copy(productsInCart = HashMap(updatedProductsInCart))
        }
    }
}