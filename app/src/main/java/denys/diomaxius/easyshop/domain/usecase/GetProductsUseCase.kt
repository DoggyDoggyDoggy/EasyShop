package denys.diomaxius.easyshop.domain.usecase

import denys.diomaxius.easyshop.data.model.Product
import denys.diomaxius.easyshop.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): List<Product> = productRepository.getProducts()
}