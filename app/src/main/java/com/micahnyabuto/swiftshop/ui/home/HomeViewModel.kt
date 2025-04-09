package com.micahnyabuto.swiftshop.ui.home

import androidx.lifecycle.ViewModel
import com.micahnyabuto.swiftshop.repository.ProductRepository
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.micahnyabuto.swiftshop.SwiftShopApplication
import com.micahnyabuto.swiftshop.data.Product
import com.micahnyabuto.swiftshop.ui.cart.CartItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException


class ProductViewModel(private val repository: ProductRepository):ViewModel(){
    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val _cart = MutableStateFlow<List<CartItem>>(emptyList())
    val cart = _cart.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun loadProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            homeUiState = try {
                val listResult = repository.getProducts()
                HomeUiState.Success(products = listResult)
            } catch (e: IOException){
                HomeUiState.Error
            }
//            try {
//                _products.value = repository.getProducts()
//                Log.e("ProductsViewModel", "$_products")
//            } catch (e: Exception) {
//                _error.value = "Failed to load products: ${e.localizedMessage}"
//            } finally {
//                _isLoading.value = false
//            }
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            val currentCart = _cart.value.toMutableList()
            val existingItem = currentCart.find { it.product.id == product.id }
            if (existingItem != null) {
                existingItem.quantity++
                Log.d(
                    "ProductViewModel",
                    "Increased quantity for ${product.title}. New quantity: ${existingItem.quantity}"
                )
            } else {
                currentCart.add(CartItem(product))
                Log.d("ProductViewModel", "Added new item to cart: ${product.title}")
            }
            _cart.value = currentCart
            logCartContents()
        }
    }

    private fun logCartContents() {
        Log.d("ProductViewModel", "Current cart contents:")
        _cart.value.forEach { item ->
            Log.d("ProductViewModel", "${item.product.title} - Quantity: ${item.quantity}")
        }
    }


    fun increaseQuantity(product: Product) {
        viewModelScope.launch {
            val currentCart = _cart.value.toMutableList()
            val item = currentCart.find { it.product.id == product.id }
            if (item != null) {
                item.quantity++
                _cart.value = currentCart
            }
        }
    }

    fun decreaseQuantity(product: Product) {
        viewModelScope.launch {
            val currentCart = _cart.value.toMutableList()
            val item = currentCart.find { it.product.id == product.id }
            if (item != null) {
                if (item.quantity > 1) {
                    item.quantity--
                } else {
                    currentCart.remove(item)
                }
                _cart.value = currentCart
            }
        }
    }

    fun removeFromCart(product: Product) {
        viewModelScope.launch {
            val currentCart = _cart.value.toMutableList()
            currentCart.removeAll { it.product.id == product.id }
            _cart.value = currentCart
        }
    }

    fun clearCart() {
        _cart.value = emptyList()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SwiftShopApplication)
                val productRepository = application.container.productRepository
                ProductViewModel(repository = productRepository)
            }

        }
    }



}

sealed interface HomeUiState{
    data object Loading: HomeUiState
    data object Error: HomeUiState
    data class Success(val products:List<Product>) :HomeUiState

}