package denys.diomaxius.easyshop.domain.repository

interface CartRepository {
    suspend fun addItemToCart(productId: String)
}