package denys.diomaxius.easyshop.domain.repository

import denys.diomaxius.easyshop.data.model.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}