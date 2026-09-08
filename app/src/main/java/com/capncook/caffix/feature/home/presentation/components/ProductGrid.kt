package com.capncook.caffix.feature.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.capncook.caffix.feature.home.domain.model.Product


@Composable
fun ProductsGrid(products: List<Product>){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 0.dp)
    ) {

        products.chunked(2).forEach { rowItems ->

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                ProductCard(
                    product = rowItems[0],
                    modifier = Modifier.weight(1f),
                    onProductClick = {}
                )

                if(rowItems.size == 2){
                    ProductCard(
                        product = rowItems[1],
                        modifier = Modifier.weight(1f),
                        onProductClick = {}
                    )
                }else{
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}