package com.micahnyabuto.swiftshop.ui.cart

import com.micahnyabuto.swiftshop.data.Product

data class CartItem(
    val product: Product,
    var quantity: Int=1
)