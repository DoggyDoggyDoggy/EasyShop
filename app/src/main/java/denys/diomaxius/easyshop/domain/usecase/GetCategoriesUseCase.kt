package denys.diomaxius.easyshop.domain.usecase

import denys.diomaxius.easyshop.data.model.Category
import denys.diomaxius.easyshop.data.repository.CategoryRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(): List<Category> = categoryRepository.getCategories()
}