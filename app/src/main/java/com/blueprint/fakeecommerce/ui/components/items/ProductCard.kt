package com.blueprint.fakeecommerce.ui.components.items


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.blueprint.fakeecommerce.model.Product
import com.blueprint.fakeecommerce.ui.components.container.CartButtonContainer
import com.blueprint.fakeecommerce.ui.components.utils.Skeleteable

@Composable
fun ProductCard(product: Product, isLoading: Boolean = false) {
    Box(
        modifier = Modifier
            .padding(4.dp)
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column{

                Skeleteable(isLoading = isLoading) { modifier ->
                    AsyncImage(
                        model = product.image,
                        contentDescription = product.title,
                        modifier = modifier
                            .then(Modifier.fillMaxWidth())
                            .then(Modifier.height(200.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Skeleteable(isLoading = isLoading) { modifier ->
                        Text(
                            modifier = modifier,
                            text = product.title,
                            style = typography.bodyMedium)
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Skeleteable(isLoading = isLoading) { modifier ->
                        Text(
                            modifier = modifier,
                            text = "${product.price} USD",
                            style = typography.bodyLarge)
                    }
                    Column(
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ){
                        Skeleteable(isLoading = isLoading) { modifier ->
                            Box(
                                modifier = modifier
                            ){
                                CartButtonContainer(product)
                            }
                        }
                    }

                }

            }
        }
    }
}