package denys.diomaxius.easyshop.ui.screen.home.pages.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import coil3.compose.AsyncImage
import denys.diomaxius.easyshop.data.model.Product


@Composable
fun CartPage(
    modifier: Modifier = Modifier,
    viewModel: CartPageViewModel = hiltViewModel()
) {
    val cartItems by viewModel.cartItemsUi.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCart()
    }

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
            items(cartItems) { productList ->
                if (productList.quantity > 0) {
                    CartItem(
                        product = productList.product,
                        qty = productList.quantity,
                        addItemToCart = { viewModel.addItemToCart(productList.product.id) },
                        removeItemFromCart = { viewModel.removeItemFromCart(productList.product.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun CartItem(
    product: Product,
    qty: Long,
    addItemToCart: () -> Unit,
    removeItemFromCart: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.Top
            ) {
                AsyncImage(
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp),
                    model = product.images.getOrNull(0),
                    contentDescription = product.title
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        text = product.title,
                        maxLines = 1,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "$" + product.price,
                            fontSize = 14.sp,
                            style = TextStyle(
                                textDecoration = TextDecoration.LineThrough
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "$" + product.actualPrice,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        IconButton(
                            onClick = {
                                removeItemFromCart()
                            }
                        ) {
                            Text(
                                text = "-",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Text(
                            text = "$qty"
                        )

                        IconButton(
                            onClick = {
                                addItemToCart()
                            }
                        ) {
                            Text(
                                text = "+",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}