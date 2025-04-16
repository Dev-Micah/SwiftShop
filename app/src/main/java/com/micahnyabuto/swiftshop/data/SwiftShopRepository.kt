package com.micahnyabuto.swiftshop.data

import com.micahnyabuto.swiftshop.data.network.SwiftShopApiService


interface SwiftShopRepository {
    suspend fun getProducts(): List<Product>
}


