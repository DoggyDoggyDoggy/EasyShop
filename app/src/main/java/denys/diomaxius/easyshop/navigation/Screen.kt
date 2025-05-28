package denys.diomaxius.easyshop.navigation

sealed class Screen(val route: String) {
    object Auth : Screen("auth")
    object Login : Screen("login")
    object Signup : Screen("signup")
    object Home : Screen("home")
    object CategoryProduct : Screen("category_product")
    object ProductDetails : Screen("product_details")
    object Checkout : Screen("checkout")
}