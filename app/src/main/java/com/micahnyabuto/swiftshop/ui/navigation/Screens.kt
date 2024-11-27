package com.micahnyabuto.swiftshop.ui.navigation

sealed class Screens(val route: String) {
    data object Home : Screens ("home")
    data object Checkout : Screens ("checkout_screen")
    data object OrderPlaced: Screens("order_placed_screen")
    data object ProductDetailsScreen: Screens("product_details_screen?product={product}")

}