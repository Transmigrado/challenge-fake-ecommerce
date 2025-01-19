package com.blueprint.fakeecommerce.ui.components.groups


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.blueprint.fakeecommerce.R
import com.blueprint.fakeecommerce.ui.components.items.CategoryRow

@Composable
fun CategoryList(
    selectedCategory: String?,
    categories: List<String>,
    onClick: (category: String) -> Unit) {

    LazyColumn(modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxHeight()) {

        item {
            Spacer(Modifier.height(12.dp))
            Text(stringResource(R.string.drawer_title), modifier = Modifier.padding(16.dp))
            HorizontalDivider()
            Text(
                stringResource(R.string.drawer_categories),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }

        item {
            CategoryRow(selectedCategory,stringResource(R.string.drawer_all), isAllCategory = true, onClick = {
                onClick("all")
            })
        }

        items(categories.size){ index ->
            CategoryRow(selectedCategory,categories[index], onClick = {
                onClick(categories[index])
            })
        }

    }
}