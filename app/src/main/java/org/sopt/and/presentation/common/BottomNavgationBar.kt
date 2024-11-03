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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import org.sopt.and.R
import org.sopt.and.navigation.Screen

@Composable
fun BottomNavigationBar(
    navController: NavController,
    currentRoute: String?
) {
    NavigationBar {
        for (item in BottomNavItem.values()) {
            NavigationBarItem(
                icon = item.icon,
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) }
            )
        }
    }
}

enum class BottomNavItem(
    val route: String,
    val icon: @Composable () -> Unit,
    val label: String
) {
    Home(
        route = Screen.Home.route,
        icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
        label = "홈"
    ),
    Search(
        route = Screen.Search.route,
        icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
        label = "검색"
    ),
    MyPage(
        route = Screen.MyPage.route,
        icon = { Icon(Icons.Filled.Person, contentDescription = "MY") },
        label = "MY"
    )
}