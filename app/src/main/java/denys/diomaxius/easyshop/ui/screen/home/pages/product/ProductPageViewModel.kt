package denys.diomaxius.easyshop.ui.screen.home.pages.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.easyshop.data.model.Product
import denys.diomaxius.easyshop.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductPageViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
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
        }
    }
}