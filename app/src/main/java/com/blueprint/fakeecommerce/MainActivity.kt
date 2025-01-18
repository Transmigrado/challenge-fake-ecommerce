package com.blueprint.fakeecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.blueprint.fakeecommerce.store.reducers.AppState
import com.blueprint.fakeecommerce.store.reducers.RootReducer
import com.blueprint.fakeecommerce.ui.screens.HomeScreen
import com.blueprint.fakeecommerce.ui.theme.FakeECommerceTheme
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.compose.StoreProvider
import org.reduxkotlin.threadsafe.createThreadSafeStore
import org.reduxkotlin.thunk.createThunkMiddleware

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val store = createThreadSafeStore(
            RootReducer,
            AppState(),
            applyMiddleware(
                createThunkMiddleware()
            )
        )

        setContent {
            FakeECommerceTheme {
                StoreProvider(store) {
                    HomeScreen()
                }
            }
        }
    }
}
