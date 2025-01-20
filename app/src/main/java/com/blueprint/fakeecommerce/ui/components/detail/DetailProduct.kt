package com.blueprint.fakeecommerce.ui.components.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.blueprint.fakeecommerce.model.Product
import com.blueprint.fakeecommerce.ui.components.info.Rating
import com.blueprint.fakeecommerce.ui.components.utils.Skeleteable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.blueprint.fakeecommerce.ui.components.container.CartButtonContainer

@Composable
fun DetailProduct(product: Product){
    Column{
        Column {
            Skeleteable(isLoading = false) { modifier ->
                AsyncImage(
                    model = product.image,
                    contentDescription = product.title,
                    modifier = modifier
                        .then(Modifier.fillMaxWidth())
                        .then(Modifier.height(300.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(product.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text("${product.price}")
            }


            Text(product.description, fontSize = 16.sp, fontWeight = FontWeight.Normal)
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Rating(3.5F)
            CartButtonContainer(product)
        }


    }
}