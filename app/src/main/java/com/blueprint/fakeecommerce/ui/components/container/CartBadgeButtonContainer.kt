package com.blueprint.fakeecommerce.ui.components.container

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.blueprint.fakeecommerce.store.reducers.AppState
import com.blueprint.fakeecommerce.ui.components.CartBadgeButton
import org.reduxkotlin.compose.selectState

@Composable
fun CartBadgeButtonContainer(navController: NavHostController) {

    val productsInCart by selectState<AppState, HashMap<Int, Int>> { productsCartState.productsInCart }
    val totalSum = productsInCart.values.sum()

    CartBadgeButton(totalSum) {
        navController.navigate("productDetail")
    }

}