package denys.diomaxius.easyshop.ui.screen.home.pages.product

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import denys.diomaxius.easyshop.data.model.Product

@Composable
fun CategoryProductPage(
    categoryId: String,
    viewModel: ProductPageViewModel = hiltViewModel()
) {
    val products by viewModel.products.collectAsState()

    LazyColumn(
        modifier = Modifier
            .padding(
                horizontal = 8.dp,
                vertical = 32.dp
            )
            .fillMaxSize()
    ) {
        items(products.chunked(2)) { rowProducts ->
            Row {
                rowProducts.forEach {
                    ProductItemView(
                        modifier = Modifier.weight(1f),
                        product = it
                    )
                }
                if (rowProducts.size == 1) {
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun ProductItemView(
    modifier: Modifier = Modifier,
    product: Product
) {
    Card(
        modifier = modifier
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(8.dp),

        ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 8.dp
            )
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth(),
                model = product.images.getOrNull(0),
                contentDescription = product.title
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = product.title,
                maxLines = 1,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
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

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Add to cart"
                    )
                }
            }
        }
    }
}