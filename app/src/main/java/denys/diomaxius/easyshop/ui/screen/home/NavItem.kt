package denys.diomaxius.easyshop.ui.screen.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
)

object NavItems {
    val list = listOf<NavItem>(
        NavItem(
            label = "Home",
            icon = Icons.Default.Home,
            contentDescription = "Home"
        ),
        NavItem(
            label = "Cart",
            icon = Icons.Default.ShoppingCart,
            contentDescription = "Cart"
        ),
        NavItem(
            label = "Favorite",
            icon = Icons.Default.Favorite,
            contentDescription = "Favorite"
        ),
        NavItem(
            label = "Profile",
            icon = Icons.Default.Person,
            contentDescription = "Profile"
        )
    )
}
