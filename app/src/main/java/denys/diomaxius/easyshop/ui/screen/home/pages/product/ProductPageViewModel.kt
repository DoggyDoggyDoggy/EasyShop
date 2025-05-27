package denys.diomaxius.easyshop.ui.screen.home.pages.product

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.easyshop.data.model.Product
import denys.diomaxius.easyshop.domain.usecase.AddItemToCartUseCase
import denys.diomaxius.easyshop.domain.usecase.GetProductsUseCase
import denys.diomaxius.easyshop.utils.AppUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductPageViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val addItemToCartUseCase: AddItemToCartUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val categoryId: String = checkNotNull(savedStateHandle["categoryId"])

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            _products.value = getProductsUseCase(categoryId)
        //Testing purpose to multiple products to see hows is look like
        //_products.value = _products.value.plus(_products.value).plus(_products.value).plus(_products.value)
        }
    }

    fun addItemToCart(productId: String, context: Context) {
        viewModelScope.launch {
            try {
                addItemToCartUseCase(productId)
                AppUtil.showToast(
                    context = context,
                    message = "Item added to cart"
                )
            } catch (e: Exception) {
                AppUtil.showToast(
                    context = context,
                    message = "Something went wrong"
                )
            }
        }
    }
}