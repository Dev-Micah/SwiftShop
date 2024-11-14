package com.micahnyabuto.swiftshop.data



interface SwiftShopRepository {
    suspend fun getProducts(): List<Product>
}