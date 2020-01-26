package com.auto.autoads.model.login

import com.auto.autoads.R
import com.auto.autoads.model.ApplicationProvider
import com.auto.autoads.model.SpManager
import com.auto.autoads.model.utils.convertFirebaseUserToAppUser
import com.google.firebase.auth.FirebaseAuth

object LoginManager {

    private val firebaseInstance = FirebaseAuth.getInstance()

    fun registerUser(email: String, password: String, listener: IRegisterListener) {
        firebaseInstance.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                try {
                    it.result?.user?.let { firebaseUser ->
                        convertFirebaseUserToAppUser(
                            firebaseUser,
                            password
                        )
                    }?.let { user ->
                        listener.onRegisterSuccess(
                            user
                        )
                    }
                } catch (e: Exception) {
                    listener.onRegisterError(
                        e.message ?: ApplicationProvider.instance.getString(R.string.unknown_error)
                    )
                }
            }
            .addOnFailureListener {
                listener.onRegisterError(
                    it.message ?: ApplicationProvider.instance.getString(R.string.unknown_error)
                )
            }
    }

    fun loginUser(email: String, password: String, listener: ILoginListener) {
        firebaseInstance.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val user = convertFirebaseUserToAppUser(
                    firebaseInstance.currentUser!!,
                    password
                )
                listener.onLoginSuccess(user)
                SpManager.saveUser(user)
            }
            .addOnFailureListener {
                listener.onLoginError(
                    it.message ?: ApplicationProvider.instance.getString(R.string.unknown_error)
                )
            }
    }

    fun changeUserPass(
        email: String,
        callback: (String) -> Unit,
        error: (String) -> Unit
    ) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnSuccessListener {
                callback.invoke("Пароль был успешно сброшен. Проверьте почту!")
            }
            .addOnFailureListener {
                error.invoke(it.toString())
            }
    }
}