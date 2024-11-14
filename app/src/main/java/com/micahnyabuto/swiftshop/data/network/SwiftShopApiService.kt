package com.micahnyabuto.swiftshop.data.network

import com.micahnyabuto.swiftshop.data.Product
import retrofit2.http.GET

interface SwiftShopApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}