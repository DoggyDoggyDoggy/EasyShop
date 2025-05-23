package denys.diomaxius.easyshop.ui.screen.home.pages.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.WormIndicatorType
import denys.diomaxius.easyshop.data.model.Category
import denys.diomaxius.easyshop.navigation.LocalNavController
import denys.diomaxius.easyshop.navigation.Screen

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    viewModel: HomePageViewModel = hiltViewModel()
) {
    val banners by viewModel.banners.collectAsState()
    val categories by viewModel.categories.collectAsState()
    val navHostController = LocalNavController.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        BannerView(bannerList = banners)

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Categories",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        CategoryView(
            categoryList = categories,
            navHostController = navHostController
        )
    }
}

@Composable
fun CategoryView(
    categoryList: List<Category>,
    navHostController: NavHostController
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categoryList) { category ->
            CategoryItem(
                category = category,
                navHostController = navHostController
            )
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    navHostController: NavHostController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    navHostController.navigate("${Screen.CategoryProduct.route}/${category.id}") {
                        launchSingleTop = true
                    }
                }
            ,
            model = category.imageUrl,
            contentScale = ContentScale.Fit,
            contentDescription = category.name
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = category.name,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun BannerView(
    modifier: Modifier = Modifier,
    bannerList: List<String>
) {
    Column(modifier = modifier) {
        val pagerState = rememberPagerState(0) { bannerList.size }

        HorizontalPager(
            state = pagerState,
            pageSpacing = 24.dp
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                model = bannerList[it],
                contentDescription = "Banner image"
            )

        }

        Spacer(modifier = Modifier.height(12.dp))

        DotsIndicator(
            dotCount = bannerList.size,
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