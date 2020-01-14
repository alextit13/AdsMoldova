package com.auto.autoads.model.login

import com.auto.autoads.model.utils.User

interface ILoginListener {
    fun onLoginSuccess(user: User)
    fun onLoginError(error: String)
}