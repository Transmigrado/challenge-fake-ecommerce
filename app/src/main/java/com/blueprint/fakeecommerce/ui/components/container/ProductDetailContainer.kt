package com.blueprint.fakeecommerce.ui.components.container

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.blueprint.fakeecommerce.model.Product
import com.blueprint.fakeecommerce.store.reducers.AppState
import com.blueprint.fakeecommerce.ui.components.detail.DetailProduct
import org.reduxkotlin.compose.selectState

@Composable
fun ProductDetailContainer() {
    val product by selectState<AppState, Product?> { productsState.selectedProduct }

    if(product == null){
        Text("Producto no disponible")
    } else {
        DetailProduct(product!!)
    }
}