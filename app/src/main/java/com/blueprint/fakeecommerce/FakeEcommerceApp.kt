package com.blueprint.fakeecommerce

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FakeEcommerceApp: Application() {
    override fun onCreate() {
        super.onCreate()

    }
}