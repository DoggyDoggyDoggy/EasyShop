package denys.diomaxius.easyshop.ui.screen.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.easyshop.domain.usecase.SignUpUseCase
import javax.inject.Inject

@HiltViewModel
class SignupScreenViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    fun signup(
        email: String,
        name: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) = signUpUseCase(email = email, name = name, password = password, onResult = onResult)
}