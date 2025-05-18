package denys.diomaxius.easyshop.ui.screen.home.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CategoryProductPage(
    categoryId: String
) {
    Text(
        text = "Category product page: $categoryId"
    )
}