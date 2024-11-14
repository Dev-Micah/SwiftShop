package com.micahnyabuto.swiftshop

import android.app.Application
import com.micahnyabuto.swiftshop.data.DefaultAppContainer
import com.micahnyabuto.swiftshop.data.SwiftShopContainer

class SwiftShopApplication: Application()  {
    lateinit var container: SwiftShopContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}