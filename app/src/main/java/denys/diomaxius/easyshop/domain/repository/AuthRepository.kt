package denys.diomaxius.easyshop.domain.repository

interface AuthRepository {
    fun login(email: String, password: String, onResult: (Boolean, String) -> Unit)
    fun signup(email: String, name: String, password: String, onResult: (Boolean, String) -> Unit)
}