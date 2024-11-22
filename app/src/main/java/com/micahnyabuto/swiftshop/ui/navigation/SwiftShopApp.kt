package com.micahnyabuto.swiftshop.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.micahnyabuto.swiftshop.ui.home.HomeScreen
import com.micahnyabuto.swiftshop.ui.home.ProductViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwiftShopApp(){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {SwiftTopAppBar(scrollBehavior= scrollBehavior)}
    ){
       Surface(
           modifier = Modifier.fillMaxSize()
       ){
           val productViewModel: ProductViewModel=viewModel()
           HomeScreen(
               homeUiState = productViewModel.homeUiState,
               contentPadding = it,
               viewModel = TODO(),
           )
       }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwiftTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,modifier: Modifier=Modifier
){
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Swift Shop",
                //modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color.Red,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif)
        },
        modifier=modifier
    )
}