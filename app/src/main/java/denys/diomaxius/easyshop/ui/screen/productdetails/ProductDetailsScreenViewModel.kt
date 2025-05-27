package denys.diomaxius.easyshop.ui.screen.productdetails

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.easyshop.data.model.Product
import denys.diomaxius.easyshop.domain.usecase.AddItemToCartUseCase
import denys.diomaxius.easyshop.domain.usecase.GetProductDetailsUseCase
import denys.diomaxius.easyshop.utils.AppUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsScreenViewModel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val addItemToCartUseCase: AddItemToCartUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val productId: String = checkNotNull(savedStateHandle["productId"])

    private val _product = MutableStateFlow<Product>(Product())
    val product: StateFlow<Product> = _product.asStateFlow()

    init {
        getProduct()
    }

    fun getProduct() {
        viewModelScope.launch {
            _product.value = getProductDetailsUseCase(productId)
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