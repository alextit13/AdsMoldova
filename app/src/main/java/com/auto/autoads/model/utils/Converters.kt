package com.auto.autoads.model.utils

import com.google.firebase.auth.FirebaseUser

fun convertFirebaseUserToAppUser(firebaseUser: FirebaseUser, password: String) = User().apply {
    email = firebaseUser.email ?: "error"
    login = firebaseUser.displayName ?: email.substringBeforeLast("@")
    this.password = password
}