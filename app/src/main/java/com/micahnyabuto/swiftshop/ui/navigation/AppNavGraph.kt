package com.micahnyabuto.swiftshop.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.micahnyabuto.swiftshop.ui.account.AccountScreen
import com.micahnyabuto.swiftshop.ui.cart.CartScreen
import com.micahnyabuto.swiftshop.ui.home.HomeScreen
import com.micahnyabuto.swiftshop.ui.home.ProductViewModel
import com.micahnyabuto.swiftshop.ui.order.OrderPlacedScreen
import com.micahnyabuto.swiftshop.ui.productdetails.ProductDetailViewModel
import com.micahnyabuto.swiftshop.ui.productdetails.ProductDetailsScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {HomeScreen() }
            composable(Screen.Cart.route) { CartScreen() }
            composable(Screen.Account.route) { AccountScreen() }
            composable("productDetails") { ProductDetailsScreen() }
            composable("orderPlaced") { OrderPlacedScreen(
                navController = navController
            ) }
        }
    }
}
