package org.sopt.and.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.sopt.and.navigation.Screen

@Composable
fun BottomNavigationBar(
    navController: NavController,
    currentRoute: String?
) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("홈") },
            selected = currentRoute == Screen.Home.route,
            onClick = { navController.navigate(Screen.Home.route) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            label = { Text("검색") },
            selected = currentRoute == Screen.Search.route,
            onClick = { navController.navigate(Screen.Search.route) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "MY") },
            label = { Text("MY") },
            selected = currentRoute == Screen.MyPage.route,
            onClick = { navController.navigate(Screen.MyPage.route) }
        )
    }
}