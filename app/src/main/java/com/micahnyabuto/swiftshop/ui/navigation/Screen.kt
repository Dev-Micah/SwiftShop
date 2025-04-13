package com.micahnyabuto.swiftshop.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String , val icon: ImageVector) {

     object Home : Screen("home" , title = "Home" , icon = Icons.Default.Home)
     object Cart : Screen("cart" , title = "Cart" , icon = Icons.Default.ShoppingCart)
//   object OrderPlaced: Screen("order_placed_screen")
//   object ProductDetailsScreen: Screen("product_details_screen?product={product}")
     object Account: Screen ("account", title = "Account", icon = Icons.Default.Person)


}