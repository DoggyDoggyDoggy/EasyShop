package denys.diomaxius.easyshop.ui.screen.checkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CheckOutScreen(
    viewModel: CheckOutScreenViewModel = hiltViewModel()
) {
    val cartItems by viewModel.cartItemsUi.collectAsState()
    val total by viewModel.total.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Checkout",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 4.dp),
            thickness = 2.dp
        )

        LazyColumn {
            items(cartItems) {
                if (it.quantity > 0) {
                    ItemCheckoutRow(
                        name = it.product.title,
                        qty = it.quantity,
                        price = it.product.actualPrice.toInt()
                    )
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 4.dp),
            thickness = 2.dp
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier,
                text = "Total",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )

            Text(
                modifier = Modifier,
                text = "$$total",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun ItemCheckoutRow(
    name: String,
    qty: Long,
    price: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            fontSize = 16.sp,
            modifier = Modifier
                .weight(3f)
                .padding(end = 8.dp)
        )

        Text(
            text = "x$qty",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
        )

        Text(
            text = "$${qty * price}",
            fontSize = 16.sp,
            textAlign = TextAlign.End,
            modifier = Modifier
                .weight(1f)
        )
    }
}

















