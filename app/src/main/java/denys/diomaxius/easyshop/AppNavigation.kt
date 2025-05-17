package denys.diomaxius.easyshop

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import denys.diomaxius.easyshop.screen.AuthScreen
import denys.diomaxius.easyshop.screen.HomeScreen
import denys.diomaxius.easyshop.screen.LoginScreen
import denys.diomaxius.easyshop.screen.SignUpScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navHostController: NavHostController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = "auth"
    ) {
        composable(route = "auth") {
            AuthScreen(
                modifier = modifier,
                navHostController = navHostController
            )
        }

        composable(route = "login") {
            LoginScreen(
                modifier,
                navHostController = navHostController
            )
        }

        composable(route = "signup") {
            SignUpScreen(
                modifier,
                navHostController = navHostController
            )
        }

        composable(route = "home") {
            HomeScreen()
        }
    }
}