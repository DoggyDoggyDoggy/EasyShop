package denys.diomaxius.easyshop.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import denys.diomaxius.easyshop.domain.repository.CartRepository
import kotlinx.coroutines.tasks.await

class CartRepositoryImpl(
    private val firestore: FirebaseFirestore = Firebase.firestore,
    private val auth: FirebaseAuth = Firebase.auth
) : CartRepository {
    override suspend fun addItemToCart(productId: String) {
        firestore
            .collection("users")
            .document(auth.uid!!)
            .update("cartItems.$productId", FieldValue.increment(1))
            .await()
    }
}