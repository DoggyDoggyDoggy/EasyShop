package denys.diomaxius.easyshop.domain.usecase

import denys.diomaxius.easyshop.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        email: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) = authRepository.login(email, password, onResult)
}