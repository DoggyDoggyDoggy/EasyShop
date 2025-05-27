package denys.diomaxius.easyshop.domain.repository

import denys.diomaxius.easyshop.data.model.Product

interface ProductDetailsRepository {
    suspend fun getProductDetails(productId: String): Product
}