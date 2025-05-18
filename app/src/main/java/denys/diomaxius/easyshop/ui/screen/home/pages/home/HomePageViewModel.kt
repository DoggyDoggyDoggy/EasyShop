package denys.diomaxius.easyshop.ui.screen.home.pages.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.easyshop.data.model.Category
import denys.diomaxius.easyshop.domain.usecase.GetBannersUrlsUseCase
import denys.diomaxius.easyshop.domain.usecase.GetCategoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getBannersUrlsUseCase: GetBannersUrlsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {
    private val _banners = MutableStateFlow<List<String>>(emptyList())
    val banners: StateFlow<List<String>> = _banners.asStateFlow()

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    init {
        getBannersUrls()
        getCategories()
    }

    fun getBannersUrls() {
        viewModelScope.launch {
            _banners.value = getBannersUrlsUseCase()
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            _categories.value = getCategoriesUseCase()
        }
    }
}