package com.auto.autoads.model

import android.content.Context
import com.auto.autoads.model.utils.User
import com.google.gson.Gson

object SpManager {

    private const val SP_USER = "sp_user"
    private const val SP_USER_KEY = "sp_user_key"

    fun saveUser(user: User) {
        ApplicationProvider.instance.getSharedPreferences(SP_USER, Context.MODE_PRIVATE)
            .edit()
            .putString(SP_USER_KEY, Gson().toJson(user))
            .apply()
    }

    fun getUser(): User? {
        val stringUser =
            ApplicationProvider.instance.getSharedPreferences(SP_USER, Context.MODE_PRIVATE)
                .getString(SP_USER_KEY, "")
        return Gson().fromJson(stringUser, User::class.java)
    }

    fun deleteUser() {
        ApplicationProvider.instance.getSharedPreferences(SP_USER, Context.MODE_PRIVATE)
            .edit()
            .putString(SP_USER_KEY, "")
            .apply()
    }

    fun getUserRegCode(): String = ApplicationProvider.instance
        .getSharedPreferences("SP_CODE_REG", Context.MODE_PRIVATE)
        .getString("code", "") ?: ""

    fun setUserRegCode(code: String) {
        ApplicationProvider.instance
            .getSharedPreferences("SP_CODE_REG", Context.MODE_PRIVATE)
            .edit()
            .putString("code", code)
            .apply()
    }
}