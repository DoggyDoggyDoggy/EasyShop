package denys.diomaxius.easyshop.data.repository

import denys.diomaxius.easyshop.data.model.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}