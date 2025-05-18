package denys.diomaxius.easyshop.ui.screen.home.pages.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun CategoryProductPage(
    categoryId: String,
    viewModel: ProductPageViewModel = hiltViewModel()
) {
    val products by viewModel.products.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Category product page: $categoryId"
        )
        Text(text = "Products name:")
        products.forEach {
            Text(text = it.title)
        }
    }
}