package denys.diomaxius.easyshop.ui.screen.home.pages.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.easyshop.domain.usecase.GetBannersUrlsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getBannersUrlsUseCase: GetBannersUrlsUseCase
) : ViewModel() {
    private val _banners = MutableStateFlow<List<String>>(emptyList())
    val banners: StateFlow<List<String>> = _banners.asStateFlow()

    init {
        getBannersUrls()
    }

    fun getBannersUrls() {
        viewModelScope.launch {
            _banners.value = getBannersUrlsUseCase()
        }
    }
}