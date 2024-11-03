package org.sopt.and.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.and.R
import org.sopt.and.core.UserInfo
import org.sopt.and.navigation.NavGraph
import org.sopt.and.navigation.Screen
import org.sopt.and.presentation.common.BottomNavigationBar
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.utils.KeyStorage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen(intent.getStringExtra(KeyStorage.EMAIL))
        }
    }
}

@Composable
fun MainScreen(userEmail: String?) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // 현재 화면이 메인 화면들(Home, Search, MyPage)인지 확인
    val shouldShowBottomBar = currentRoute in listOf(
        Screen.Home.route,
        Screen.Search.route,
        Screen.MyPage.route
    )

    ANDANDROIDTheme(true) {
        Scaffold(
            bottomBar = {
                if (shouldShowBottomBar) {
                    BottomNavigationBar(
                        navController = navController,
                        currentRoute = currentRoute
                    )
                }
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                NavGraph(
                    navController = navController,
                    userInfo = UserInfo(userEmail)
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen("")
}