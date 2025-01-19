package com.blueprint.fakeecommerce.ui.components.container

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.blueprint.fakeecommerce.di.CategoriesThunkEntryPoint
import com.blueprint.fakeecommerce.store.reducers.AppState
import com.blueprint.fakeecommerce.ui.components.groups.CategoryList
import dagger.hilt.android.EntryPointAccessors
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.compose.selectState

@Composable
fun CategoryListContainer() {

    val context = LocalContext.current
    val dispatch = rememberDispatcher()

    val categories by selectState<AppState, List<String>> { categoryState.list }

    val thunk = remember {
        EntryPointAccessors.fromApplication(context, CategoriesThunkEntryPoint::class.java).getCategoriesThunk()
    }

    LaunchedEffect(Unit) {
        dispatch(thunk.getProducts())
    }

    CategoryList(categories)
}