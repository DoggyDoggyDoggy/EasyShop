package denys.diomaxius.easyshop.domain.usecase

import android.content.Context
import denys.diomaxius.easyshop.domain.repository.CartRepository
import javax.inject.Inject

class AddItemToCartUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(productId: String, context: Context) = repository.addItemToCart(
        productId = productId,
        context = context
    )
}