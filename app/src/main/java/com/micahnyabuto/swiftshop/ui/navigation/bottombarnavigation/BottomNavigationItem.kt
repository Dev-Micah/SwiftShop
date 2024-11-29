package com.micahnyabuto.swiftshop.ui.navigation.bottombarnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.micahnyabuto.swiftshop.ui.navigation.Screens

data class BottomNavigationItem(
    val label: String ="" ,
    val icon: ImageVector =Icons.Default.Home,
    val route: String =""
){
    fun bottomNavigationItems(): List<BottomNavigationItem>{
        return listOf(
            BottomNavigationItem("Home",Icons.Default.Home,Screens.Home.route)
        )
    }
}
