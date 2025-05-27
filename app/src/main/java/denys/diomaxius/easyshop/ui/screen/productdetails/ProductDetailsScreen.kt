package denys.diomaxius.easyshop.ui.screen.productdetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsScreenViewModel = hiltViewModel(),
    productId: String
) {
    val product by viewModel.product.collectAsState()

    Text(
        text = product.title
    )
}