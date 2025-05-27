package denys.diomaxius.easyshop.ui.screen.home.pages.cart

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.easyshop.domain.usecase.GetCartItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import denys.diomaxius.easyshop.data.model.Product
import denys.diomaxius.easyshop.domain.usecase.AddItemToCartUseCase
import denys.diomaxius.easyshop.domain.usecase.GetProductDetailsUseCase
import denys.diomaxius.easyshop.domain.usecase.RemoveItemFromCartUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CartItemUiState(
    val product: Product,
    val quantity: Long
)

@HiltViewModel
class CartPageViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val addItemToCartUseCase: AddItemToCartUseCase,
    private val removeItemFromCartUseCase: RemoveItemFromCartUseCase
) : ViewModel() {
    private val _cartItemsUi = MutableStateFlow<List<CartItemUiState>>(emptyList())
    val cartItemsUi: StateFlow<List<CartItemUiState>> = _cartItemsUi.asStateFlow()

    init {
        loadCart()
    }

    fun loadCart() {
        viewModelScope.launch {
            val cartMap = getCartItemsUseCase()
            val uiList = cartMap.map { (id, qty) ->
                async {
                    val prod = getProductDetailsUseCase(id)
                    CartItemUiState(prod, qty)
                }
            }
            _cartItemsUi.value = uiList.awaitAll()
        }
    }

    fun addItemToCart(productId: String) {
        viewModelScope.launch {
            addItemToCartUseCase(productId)

            _cartItemsUi.update { list ->
                list.map {
                    if (it.product.id == productId)
                        it.copy(quantity = it.quantity + 1)
                    else it
                }
            }
        }
    }

    fun removeItemFromCart(productId: String) {
        viewModelScope.launch {
            removeItemFromCartUseCase(productId)

            _cartItemsUi.update { list ->
                list.map {
                    if (it.product.id == productId)
                        it.copy(quantity = it.quantity - 1)
                    else it
                }
            }
        }
    }
}