package denys.diomaxius.easyshop.domain.usecase

import denys.diomaxius.easyshop.data.model.Product
import denys.diomaxius.easyshop.domain.repository.ProductDetailsRepository
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val repository: ProductDetailsRepository
) {
    suspend operator fun invoke(productId: String): Product = repository.getProductDetails(productId)
}