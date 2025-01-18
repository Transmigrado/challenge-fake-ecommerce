package com.blueprint.fakeecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.blueprint.fakeecommerce.ui.screens.HomeScreen
import com.blueprint.fakeecommerce.ui.theme.FakeECommerceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FakeECommerceTheme {
                HomeScreen()
            }
        }
    }
}
