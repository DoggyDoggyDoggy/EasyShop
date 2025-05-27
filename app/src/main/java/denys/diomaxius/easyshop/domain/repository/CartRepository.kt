package denys.diomaxius.easyshop.domain.repository

interface CartRepository {
    suspend fun addItemToCart(productId: String)
    suspend fun getCartItems(): Map<String, Long>
    suspend fun removeItemFromCart(productId: String)
}