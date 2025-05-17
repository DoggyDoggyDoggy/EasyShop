package denys.diomaxius.easyshop.ui.screen.login

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginScreenViewModel : ViewModel() {
    private val auth = Firebase.auth

    fun login(
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
}