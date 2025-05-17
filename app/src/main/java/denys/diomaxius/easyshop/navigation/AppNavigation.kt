package denys.diomaxius.easyshop.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import denys.diomaxius.easyshop.ui.screen.auth.AuthScreen
import denys.diomaxius.easyshop.ui.screen.home.HomeScreen
import denys.diomaxius.easyshop.ui.screen.login.LoginScreen
import denys.diomaxius.easyshop.ui.screen.signup.SignUpScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navHostController: NavHostController = rememberNavController()

    val isLoggedIn = Firebase.auth.currentUser != null
    val firstPage = if (isLoggedIn) "home" else "auth"

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = firstPage
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
            HomeScreen(
                modifier,
                navHostController = navHostController
            )
        }
    }
}