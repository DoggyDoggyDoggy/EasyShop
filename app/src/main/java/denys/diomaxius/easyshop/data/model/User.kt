package denys.diomaxius.easyshop.data.model

data class User(
    val name: String = "",
    val email: String = "",
    val uid: String = "",
    val cartItems: Map<String, Long> = mapOf()
)
