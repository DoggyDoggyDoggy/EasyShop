package denys.diomaxius.easyshop.ui.screen.home.pages.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import denys.diomaxius.easyshop.data.model.Product


@Composable
fun CartPage(
    modifier: Modifier = Modifier,
    viewModel: CartPageViewModel = hiltViewModel()
) {
    val cartItems by viewModel.cartItemsUi.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Your cart",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        LazyColumn {
            items(cartItems) {
                CartItem(
                    product = it.product,
                    qty = it.quantity
                )
            }
        }
    }
}

@Composable
fun CartItem(
    product: Product,
    qty: Long,
) {
    Text(
        text = product.title,
        fontSize = 16.sp
    )
}