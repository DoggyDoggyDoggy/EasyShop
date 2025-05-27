package denys.diomaxius.easyshop.domain.usecase

import denys.diomaxius.easyshop.domain.repository.CartRepository
import javax.inject.Inject

class GetCartItemsUseCase @Inject constructor(
    private val cartRepository: CartRepository
){
    suspend operator fun invoke(): Map<String, Long> = cartRepository.getCartItems()
}