package com.micahnyabuto.swiftshop.ui.checkout

import com.micahnyabuto.swiftshop.data.Product

data class CartItem(
    val product: Product,
    var quantity: Int=1
)
