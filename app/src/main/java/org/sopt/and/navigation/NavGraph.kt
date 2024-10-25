package org.sopt.and.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost  // ✅ 이렇게 해야 함
import androidx.navigation.compose.composable
import org.sopt.and.core.UserInfo
import org.sopt.and.presentation.home.HomeScreen
import org.sopt.and.presentation.mypage.MyPageScreen
import org.sopt.and.presentation.search.SearchScreen


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object MyPage : Screen("mypage")
}

@Composable
fun NavGraph(navController: NavHostController, userInfo: UserInfo) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { HomeScreen() }
        composable(Screen.Search.route) { SearchScreen() }
        composable(Screen.MyPage.route) { MyPageScreen(
            userInfo = userInfo
        ) }
    }
}
