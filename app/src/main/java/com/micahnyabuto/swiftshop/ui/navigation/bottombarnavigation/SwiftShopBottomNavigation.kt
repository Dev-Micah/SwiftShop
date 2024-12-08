package com.micahnyabuto.swiftshop.ui.navigation.bottombarnavigation

import android.R.attr.icon
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.compose.ui.Modifier


@Composable
fun SwiftShopBottomNavigation(navController: NavController){
    var navigationSelectedItem by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar (containerColor = Color.Transparent){
        BottomNavigationItem().bottomNavigationItems().forEachIndexed { index, item->
            NavigationBarItem(
                selected = index== navigationSelectedItem,
                onClick = {

                    navigationSelectedItem = index
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    },
                    icon= {
                        Icon(item.icon,contentDescription =null) },
                    modifier = Modifier,
                    label = {
                        Text(

                            text= item.label
                        )

                }

            )
        }
    }
}