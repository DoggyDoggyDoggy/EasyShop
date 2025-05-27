package denys.diomaxius.easyshop.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import denys.diomaxius.easyshop.data.model.Category
import denys.diomaxius.easyshop.domain.repository.CategoryRepository
import kotlinx.coroutines.tasks.await

class CategoryRepositoryImpl(
    private val firestore: FirebaseFirestore = Firebase.firestore
) : CategoryRepository {
    override suspend fun getCategories(): List<Category> {
        val doc = firestore
            .collection("data")
            .document("stock")
            .collection("categories")
            .get()
            .await()
        return doc.documents.mapNotNull { it.toObject(Category::class.java) }
    }
}