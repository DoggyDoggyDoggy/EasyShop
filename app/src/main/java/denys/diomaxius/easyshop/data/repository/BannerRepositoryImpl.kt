package denys.diomaxius.easyshop.data.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import denys.diomaxius.easyshop.domain.repository.BannerRepository
import kotlinx.coroutines.tasks.await

class BannerRepositoryImpl (
    private val firestore: FirebaseFirestore = Firebase.firestore
) : BannerRepository {
    override suspend fun fetchBannerUrls(): List<String> {
        val doc = firestore
            .collection("data")
            .document("banners")
            .get()
            .await()
        return doc.get("urls") as? List<String> ?: emptyList()
    }
}
