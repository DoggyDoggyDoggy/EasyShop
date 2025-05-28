package denys.diomaxius.easyshop.ui.screen.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.easyshop.data.model.CartItemUiState
import denys.diomaxius.easyshop.domain.usecase.GetCartItemsUseCase
import denys.diomaxius.easyshop.domain.usecase.GetProductDetailsUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckOutScreenViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val getProductDetailsUseCase: GetProductDetailsUseCase
) : ViewModel()  {
    private val _cartItemsUi = MutableStateFlow<List<CartItemUiState>>(emptyList())
    val cartItemsUi: StateFlow<List<CartItemUiState>> = _cartItemsUi.asStateFlow()

    private val _total = MutableStateFlow(0)
    val total: StateFlow<Int> = _total.asStateFlow()

    init {
        loadCart()
    }

    fun loadCart() {
        viewModelScope.launch {
            val cartMap = getCartItemsUseCase()
            val uiList = cartMap.map { (id, qty) ->
                async {
                    val prod = getProductDetailsUseCase(id)
                    _total.value += prod.actualPrice.toInt() * qty.toInt()
                    CartItemUiState(prod, qty)
                }
            }
            _cartItemsUi.value = uiList.awaitAll()
        }
    }
}