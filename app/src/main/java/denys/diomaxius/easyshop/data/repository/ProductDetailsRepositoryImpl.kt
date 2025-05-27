package denys.diomaxius.easyshop.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import denys.diomaxius.easyshop.data.model.Product
import denys.diomaxius.easyshop.domain.repository.ProductDetailsRepository
import kotlinx.coroutines.tasks.await

class ProductDetailsRepositoryImpl(
    private val firestore: FirebaseFirestore = Firebase.firestore
) : ProductDetailsRepository {
    override suspend fun getProductDetails(productId: String): Product {

        val doc = firestore
            .collection("data").document("stock")
            .collection("products")
            .document(productId)
            .get()
            .await()
        return doc.toObject(Product::class.java) ?: Product(
            id = "",
            title = "",
            description = "",
            price = "",
            actualPrice = "",
            category = "",
            images = emptyList()
        )
    }
}