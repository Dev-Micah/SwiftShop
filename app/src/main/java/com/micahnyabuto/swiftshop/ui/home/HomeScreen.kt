package com.micahnyabuto.swiftshop.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.micahnyabuto.swiftshop.R
import com.micahnyabuto.swiftshop.data.Product

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navigateToItemDetails: @Composable (productId: Int) -> Unit,
){
    val productViewModel: ProductViewModel = viewModel(factory = ProductViewModel.Factory)
    val state = productViewModel.homeUiState

    when (state) {

        is HomeUiState.Error -> ErrorScreen()
        is HomeUiState.Loading -> CircularProgressIndicator()

        is HomeUiState.Success ->
            ProductList(
                products = state.products,
                navigateToItemDetails = { navigateToItemDetails(it) },
            )
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    products: List<Product>,
    navigateToItemDetails: @Composable (productId: Int) -> Unit
) {

    Column {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
            modifier = modifier.padding()
        ) {
            items (products){ product ->
                ProductItem(
                    product = product,
                    navigateToItemDetails = { navigateToItemDetails(product.id) },
                    productViewModel = viewModel(),
                )


            }

        }
    }
}
@Composable
fun ErrorScreen(modifier: Modifier = Modifier){
    Box(
        modifier.fillMaxSize(),contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = "Connection Error",
            contentScale = ContentScale.Crop
        )
    }
}
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Image(
            modifier = modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = "Loading",
            contentScale = ContentScale.Crop
        )
    }

}
