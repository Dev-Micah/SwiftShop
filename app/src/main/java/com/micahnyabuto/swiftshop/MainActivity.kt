package com.micahnyabuto.swiftshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.compose.SwiftShopTheme
import com.micahnyabuto.swiftshop.ui.home.HomeScreen
import com.micahnyabuto.swiftshop.ui.home.ProductViewModel
import com.micahnyabuto.swiftshop.ui.navigation.SwiftShopApp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwiftShopTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   Column(
                       modifier = Modifier
                           .padding(innerPadding)
                           .fillMaxSize()
                   ){
                       SwiftShopApp()
                   }
                }
            }
        }
    }
}

