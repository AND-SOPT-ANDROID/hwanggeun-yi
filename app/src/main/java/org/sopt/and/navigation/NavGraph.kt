package org.sopt.and.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.and.core.UserInfo
import org.sopt.and.presentation.home.HomeScreen
import org.sopt.and.presentation.mypage.MyPageScreen
import org.sopt.and.presentation.search.SearchScreen
import org.sopt.and.presentation.auth.SignInScreen
import org.sopt.and.presentation.auth.SignUpScreen


sealed class Screen(val route: String) {
    object SignIn : Screen("signin")
    object SignUp : Screen("signup")
    object Home : Screen("home")
    object Search : Screen("search")
    object MyPage : Screen("mypage")
}

@Composable
fun NavGraph(navController: NavHostController, userInfo: UserInfo) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignIn.route
    ) {
        composable(Screen.SignIn.route) {
            SignInScreen(
                modifier = Modifier,
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUp.route)
                },
                onSignInSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.SignIn.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(
                modifier = Modifier,
                onSignUpSuccess = {
                    navController.navigate(Screen.SignIn.route) {
                        popUpTo(Screen.SignUp.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) { HomeScreen() }
        composable(Screen.Search.route) { SearchScreen() }
        composable(Screen.MyPage.route) { MyPageScreen() }
    }
}
