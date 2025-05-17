package denys.diomaxius.easyshop.ui.screen.signup

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import denys.diomaxius.easyshop.data.model.UserModel

class SignupScreenViewModel : ViewModel() {
    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    fun signup(
        email: String,
        name: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val userId = it.result?.user?.uid
                val userModel = UserModel(name, email, userId!!)

                firestore.collection("users").document(userId).set(userModel)
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