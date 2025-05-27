package denys.diomaxius.easyshop.domain.repository

import android.content.Context

interface CartRepository {
    suspend fun addItemToCart(productId: String, context: Context)
}