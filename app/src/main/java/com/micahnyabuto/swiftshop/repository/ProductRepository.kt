package com.micahnyabuto.swiftshop.repository

import com.micahnyabuto.swiftshop.data.Product
import com.micahnyabuto.swiftshop.data.SwiftShopRepository
import com.micahnyabuto.swiftshop.data.network.SwiftShopApiService

class ProductRepository(private val swiftShopApiService: SwiftShopApiService): SwiftShopRepository {

    override suspend fun getProducts(): List<Product> =swiftShopApiService.getProducts()
    }


