package denys.diomaxius.easyshop.data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import denys.diomaxius.easyshop.data.model.User
import denys.diomaxius.easyshop.domain.repository.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    override fun login(
        email: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                onResult(true, "Login success")
            } else {
                onResult(false, it.exception?.localizedMessage ?: "Something went wrong")
            }
        }
    }

    override fun signup(
        email: String,
        name: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val userId = it.result?.user?.uid
                val user = User(name, email, userId!!)

                firestore.collection("users").document(userId).set(user)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            onResult(true, "Sign up success")
                        } else {
                            onResult(
                                false,
                                it.exception?.localizedMessage ?: "Something went wrong"
                            )
                        }
                    }
            } else {
                onResult(false, it.exception?.localizedMessage ?: "Sign up failed")
            }
        }
    }
}