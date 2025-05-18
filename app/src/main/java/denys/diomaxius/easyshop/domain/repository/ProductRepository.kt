package denys.diomaxius.easyshop.domain.repository

import denys.diomaxius.easyshop.data.model.Product

interface ProductRepository {
    suspend fun getProducts(categoryId: String): List<Product>
}