package com.auto.autoads.model.login

import com.auto.autoads.model.utils.User

interface IRegisterListener {
    fun onRegisterSuccess(user: User)
    fun onRegisterError(error: String)
}