package denys.diomaxius.easyshop.ui.screen.productdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.WormIndicatorType
import denys.diomaxius.easyshop.data.model.Product

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsScreenViewModel = hiltViewModel(),
    productId: String
) {
    val product by viewModel.product.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = 32.dp,
                horizontal = 16.dp
            )
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = product.title,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        ImageScroller(
            productImagesList = product.images
        )

        Spacer(modifier = Modifier.height(8.dp))

        PriceRow(
            product = product
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            onClick = {
                //TODO
            }
        ) {
            Text(
                text = "Add to Cart"
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Product description: ",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = product.description,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (product.otherDetails.isNotEmpty()) {
            Text(
                text = "Other details: ",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(2.dp))

            product.otherDetails.forEach { (key, value) ->
                Row {
                    Text(
                        text = "$key: ",
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = value
                    )
                }
            }
        }
    }
}

@Composable
fun PriceRow(
    product: Product
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$" + product.price,
            fontSize = 16.sp,
            style = TextStyle(
                textDecoration = TextDecoration.LineThrough
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "$" + product.actualPrice,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Add to cart"
            )
        }
    }
}

@Composable
fun ImageScroller(
    modifier: Modifier = Modifier,
    productImagesList: List<String>
) {
    Column(modifier = modifier) {
        val pagerState = rememberPagerState(0) { productImagesList.size }

        HorizontalPager(
            state = pagerState,
            pageSpacing = 24.dp
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                model = productImagesList[it],
                contentDescription = "Banner image"
            )

        }

        Spacer(modifier = Modifier.height(12.dp))

        DotsIndicator(
            dotCount = productImagesList.size,
            type = WormIndicatorType(
                dotsGraphic = DotGraphic(
                    12.dp,
                    borderWidth = 2.dp,
                    borderColor = MaterialTheme.colorScheme.primary,
                    color = Color.Transparent,
                ),
                wormDotGraphic = DotGraphic(
                    12.dp,
                    color = MaterialTheme.colorScheme.primary,
                )
            ),
            pagerState = pagerState
        )
    }
}