package denys.diomaxius.easyshop.data.repository

import android.util.Log
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
            .collection("data").
            document("stock")
            .collection("products")
            .whereEqualTo("category", categoryId)
            .get()
            .await()
        return doc.documents.map {
            Log.i("Rep Tag", "$it")

            val id = it.getString("id") ?: ""
            val title = it.getString("title") ?: ""
            val description = it.getString("description") ?: ""
            val price = it.getString("price") ?: ""
            val actualPrice = it.getString("actualPrice") ?: ""
            val category = it.getString("category") ?: ""
            val images = it.get("images") as? List<String> ?: emptyList()

            Product(
                id = id,
                title = title,
                description = description,
                price = price,
                actualPrice = actualPrice,
                category = category,
                images = images
            )
        }
    }
}