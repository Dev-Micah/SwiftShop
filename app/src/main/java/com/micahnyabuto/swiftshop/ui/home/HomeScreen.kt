package com.micahnyabuto.swiftshop.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.micahnyabuto.swiftshop.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.micahnyabuto.swiftshop.data.Product

@Composable
fun HomeScreen(
    state: HomeUiState,
    viewModel: ProductViewModel,
    navigateToItemDetails: (product: Product) -> Unit,
    modifier: Modifier=Modifier,
    contentPadding: PaddingValues=PaddingValues(0.dp)
    ){
    LaunchedEffect(key1 = true) {
        viewModel.loadProducts()
    }
    when (state){
        HomeUiState.Error -> ErrorScreen()
        HomeUiState.Loading -> LoadingScreen()
        is HomeUiState.Success->
            ProductList(
                products=state.products,
                viewModel = viewModel,
                navigateToItemDetails = {navigateToItemDetails(it)},
                modifier = modifier.padding(top = contentPadding.calculateTopPadding())

            )
    }
}
@Composable
fun ProductList(
   modifier: Modifier=Modifier,
    products: List<Product>,
    viewModel: ProductViewModel,
   navigateToItemDetails:(product: Product)-> Unit

   ){
    Column{

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding()
        ) {
            items(products) { product->
                ProductItem(
                    product,
                    productViewModel = viewModel,
                    navigateToItemDetails = {navigateToItemDetails(product)}

                )

            }
        }

     }

}
@Composable
fun LoadingScreen(modifier:Modifier=Modifier){
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Image(
            modifier=modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = "loading"
        )

    }

}
@Composable
fun ErrorScreen(modifier:Modifier=Modifier){
    Column(
        modifier=modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter =painterResource(id=R.drawable.ic_connection_error),
            contentDescription = "loading failed"
        )
        Text(
            text=stringResource(id=R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
    }

}