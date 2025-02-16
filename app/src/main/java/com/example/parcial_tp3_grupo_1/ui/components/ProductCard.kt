package com.example.parcial_tp3_grupo_1.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.parcial_tp3_grupo_1.R
import com.example.parcial_tp3_grupo_1.model.Product
import com.example.parcial_tp3_grupo_1.navigation.MainNavActions


@Composable
fun ProductCard(
    product: Product, onAddToCartClick: () -> Unit, navigationActions: MainNavActions
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .size(175.dp, 250.dp)
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.border_item_card_color),
                shape = RoundedCornerShape(16.dp)
            ),
        onClick = { navigationActions.navigateToProductDetail(product.id) }

    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = Color.White)
                .padding(8.dp)
//              .size(173.dp, 248.dp) Figma size
                .size(175.dp, 250.dp)
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(100.dp)
                    .fillMaxWidth(),
                alignment = Alignment.CenterStart,
                contentScale = ContentScale.Crop,
                onError = {error -> Log.w("image error", "${error.result}")}
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                overflow = TextOverflow.Ellipsis,
                softWrap = false
            )
            Text(
                text = product.category,
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "$${product.price}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.size(50.dp))
                IconButton(
                    onClick = { onAddToCartClick() },
                    modifier = Modifier.size(48.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add to cart",
                        tint = Color.White,
                        modifier = Modifier
                            .background(
                                color = colorResource(id = R.color.principal_button_color),
                                shape = RoundedCornerShape(16.dp),
                            )
                            .padding(8.dp)
                            .size(24.dp)


                    )
                }
            }

        }

    }
}

@Composable
fun AddedToCartFAB(
    show: Boolean = true, navigationActions: MainNavActions
) {
    if (show) {
        ExtendedFloatingActionButton(
            onClick = navigationActions.navigateToCart,
            icon = {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Added to cart",
                    tint = Color.White,
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.check_button_color),
                            shape = RoundedCornerShape(16.dp),
                        )
                        .padding(8.dp)
                        .size(24.dp)
                )
            },
            text = {
                Text(text = "Added to cart", color = Color.White)
                Spacer(modifier = Modifier.width(110.dp))
                Text(text = "Open Cart >", color = Color.White, fontSize = 10.sp)
            },
            modifier = Modifier
                .width(360.dp)
                .padding(8.dp),
            containerColor = colorResource(id = R.color.principal_button_color),
        )
    }
}