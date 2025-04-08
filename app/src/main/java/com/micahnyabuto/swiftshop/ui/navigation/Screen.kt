package com.micahnyabuto.swiftshop.ui.navigation

sealed class Screen(val route: String) {

     object Home : Screen("home")
     object Checkout : Screen("checkout")
     object OrderPlaced: Screen("order_placed_screen")
     object ProductDetailsScreen: Screen("product_details_screen?product={product}")

}