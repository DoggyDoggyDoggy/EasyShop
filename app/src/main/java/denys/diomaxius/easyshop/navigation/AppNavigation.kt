package denys.diomaxius.easyshop.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import denys.diomaxius.easyshop.ui.screen.auth.AuthScreen
import denys.diomaxius.easyshop.ui.screen.home.HomeScreen
import denys.diomaxius.easyshop.ui.screen.home.pages.CategoryProductPage
import denys.diomaxius.easyshop.ui.screen.login.LoginScreen
import denys.diomaxius.easyshop.ui.screen.signup.SignUpScreen

val LocalNavController = compositionLocalOf<NavHostController> {
    error("NavController not initialized")
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    val startDestination = startDestination()

    CompositionLocalProvider(LocalNavController provides  navHostController)  {
        NavHost(
            modifier = modifier,
            navController = navHostController,
            startDestination = startDestination
        ) {
            composable(route = Screen.Auth.route) {
                AuthScreen()
            }

            composable(route = Screen.Login.route) {
                LoginScreen()
            }

            composable(route = Screen.Signup.route) {
                SignUpScreen()
            }

            composable(route = Screen.Home.route) {
                HomeScreen()
            }

            composable(route = "${Screen.CategoryProduct.route}/{categoryId}") {
                val categoryId = it.arguments?.getString("categoryId")
                CategoryProductPage(categoryId = categoryId ?: "")
            }
        }
    }
}

fun startDestination(): String {
    val isLoggedIn = Firebase.auth.currentUser != null
    return if (isLoggedIn) Screen.Home.route else Screen.Auth.route
}