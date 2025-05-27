package denys.diomaxius.easyshop.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import denys.diomaxius.easyshop.data.model.Product
import denys.diomaxius.easyshop.domain.repository.ProductRepository
import kotlinx.coroutines.tasks.await

class ProductRepositoryImpl(
    private val firestore: FirebaseFirestore = Firebase.firestore
) : ProductRepository {
    override suspend fun getProducts(categoryId: String): List<Product> {
        val doc = firestore
            .collection("data").document("stock")
            .collection("products")
            .whereEqualTo("category", categoryId)
            .get()
            .await()
        return doc.documents.mapNotNull { it.toObject(Product::class.java) }
    }
}