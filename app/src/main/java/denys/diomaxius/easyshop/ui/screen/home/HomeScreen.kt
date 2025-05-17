package denys.diomaxius.easyshop.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import denys.diomaxius.easyshop.ui.screen.home.pages.CartPage
import denys.diomaxius.easyshop.ui.screen.home.pages.FavoritePage
import denys.diomaxius.easyshop.ui.screen.home.pages.HomePage
import denys.diomaxius.easyshop.ui.screen.home.pages.ProfilePage

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    var selectedIndex by remember {
        mutableStateOf(0)
    }

    Scaffold(
        bottomBar = {
            BottomBar(
                selectedIndex = selectedIndex,
                selectedIndexChange = { selectedIndex = it }
            )
        }
    ) {
        Content(
            modifier = Modifier.padding(it),
            selectedIndex = selectedIndex
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    selectedIndex: Int
) {
    when (selectedIndex) {
        0 -> HomePage(modifier)
        1 -> CartPage(modifier)
        2 -> FavoritePage(modifier)
        3 -> ProfilePage(modifier)
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    selectedIndexChange: (Int) -> Unit
) {
    NavigationBar(
        modifier = modifier
    ) {
        NavItems.list.forEachIndexed { index, navItem ->
            NavigationBarItem(
                selected = (index == selectedIndex),
                onClick = { selectedIndexChange(index) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.contentDescription
                    )
                },
                label = {
                    Text(text = navItem.label)
                }
            )
        }
    }
}