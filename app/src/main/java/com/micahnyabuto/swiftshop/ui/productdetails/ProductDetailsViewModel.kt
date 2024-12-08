package com.micahnyabuto.swiftshop.ui.productdetails

import android.os.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.micahnyabuto.swiftshop.SwiftShopApplication
import com.micahnyabuto.swiftshop.data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductDetailViewModel: ViewModel(){
    private val _state = MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Loading)
    val state =_state.asStateFlow()

    fun loadProduct(product: Product){
        _state.value = ProductDetailUiState.Success(product =product)
    }
    companion object{
        val Factory :ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SwiftShopApplication)
                ProductDetailViewModel()
            }
        }
    }
}

sealed interface ProductDetailUiState{
    data object Loading : ProductDetailUiState
    data class Success (val product: Product): ProductDetailUiState
    data class Error(val message: String): ProductDetailUiState
}