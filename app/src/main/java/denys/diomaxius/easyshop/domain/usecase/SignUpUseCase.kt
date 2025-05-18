package denys.diomaxius.easyshop.domain.usecase

import denys.diomaxius.easyshop.domain.repository.AuthRepository
import javax.inject.Inject


class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        email: String,
        name: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) = authRepository.signup(email = email, name = name, password = password, onResult = onResult)
}