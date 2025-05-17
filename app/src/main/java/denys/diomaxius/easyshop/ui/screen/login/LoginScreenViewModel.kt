package denys.diomaxius.easyshop.ui.screen.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.easyshop.domain.usecase.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    fun login(
        email: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) = loginUseCase(email = email, password = password, onResult = onResult)
}
