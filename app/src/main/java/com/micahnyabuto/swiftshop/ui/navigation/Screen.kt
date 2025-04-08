package com.micahnyabuto.swiftshop.ui.navigation

sealed class Screens(val route: String) {

     object Home : Screens ("home")
     object Checkout : Screens ("checkout_screen")
     object OrderPlaced: Screens("order_placed_screen")
     object ProductDetailsScreen: Screens("product_details_screen?product={product}")

}