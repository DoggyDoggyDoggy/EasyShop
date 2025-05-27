package denys.diomaxius.easyshop.data.repository

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import denys.diomaxius.easyshop.domain.repository.CartRepository
import denys.diomaxius.easyshop.utils.AppUtil
import kotlinx.coroutines.tasks.await

class CartRepositoryImpl(
    private val firestore: FirebaseFirestore = Firebase.firestore,
    private val auth: FirebaseAuth = Firebase.auth
) : CartRepository {
    override suspend fun addItemToCart(productId: String, context: Context) = try {
        firestore
            .collection("users")
            .document(auth.uid!!)
            .update("cartItems.$productId", FieldValue.increment(1))
            .await()
        AppUtil.showToast(context, "Item added to cart")
    } catch (e: Exception) {
        AppUtil.showToast(context, "Failed to add item to cart: ${e.message}")
    }
}