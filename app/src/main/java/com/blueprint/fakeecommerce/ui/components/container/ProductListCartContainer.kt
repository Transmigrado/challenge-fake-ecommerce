package com.blueprint.fakeecommerce.ui.components.container

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.blueprint.fakeecommerce.model.Product
import com.blueprint.fakeecommerce.store.reducers.AppState
import com.blueprint.fakeecommerce.ui.components.groups.ProductList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.blueprint.fakeecommerce.ui.components.info.PurchaseCard
import com.blueprint.fakeecommerce.utils.getTotalPrice
import org.reduxkotlin.compose.selectState

@Composable
fun ProductListCartContainer(innerPadding: PaddingValues) {

    val products by selectState<AppState, List<Product>> { productsCartState.list }
    val productsInCart by selectState<AppState, HashMap<Int, Int>> { productsCartState.productsInCart }

    val filteredProducts = products.filter { product ->
        val quantityInCart = productsInCart[product.id] ?: 0
        quantityInCart > 0
    }

    val total = getTotalPrice(filteredProducts, productsInCart)

    Box(
        modifier = Modifier.padding(innerPadding),
        contentAlignment = Alignment.BottomCenter
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
        ) {
            ProductList(filteredProducts, false)
        }

        PurchaseCard(total.toFloat())

    }

}