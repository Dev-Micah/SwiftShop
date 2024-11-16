package com.micahnyabuto.swiftshop.data

import com.micahnyabuto.swiftshop.data.network.SwiftShopApiService
import com.micahnyabuto.swiftshop.repository.ProductRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface SwiftShopContainer {
    val productRepository: ProductRepository
}

class DefaultAppContainer: SwiftShopContainer {
    private val baseUrl ="https://fakestoreapi.com/"

    private val retrofit:Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: SwiftShopApiService by lazy {
        retrofit.create(SwiftShopApiService::class.java)

    }
    override val productRepository: ProductRepository by lazy {
        ProductRepository(retrofitService)
    }


}