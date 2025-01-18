package com.blueprint.fakeecommerce.ui.screens


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.blueprint.fakeecommerce.di.ProductsThunkEntryPoint
import dagger.hilt.android.EntryPointAccessors
import org.reduxkotlin.compose.rememberDispatcher

@Composable
fun HomeScreen() {

    val context = LocalContext.current
    val thunk = remember {
        EntryPointAccessors.fromApplication(context, ProductsThunkEntryPoint::class.java).getProductsThunk()
    }

    val dispatch = rememberDispatcher()

    LaunchedEffect(Unit) {
        dispatch(thunk.getProducts())
    }

    Text("Hola Mundo")
}

