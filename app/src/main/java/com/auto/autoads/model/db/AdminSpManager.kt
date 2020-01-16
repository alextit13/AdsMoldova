package com.auto.autoads.model.db

import android.content.Context
import com.auto.autoads.model.ApplicationProvider

fun getPairLoginAdmin(): Pair<String, String> {
    val login = ApplicationProvider.instance.getSharedPreferences("ADMIN_SP", Context.MODE_PRIVATE)
        .getString("sp_admin_login", "123")
    val pass = ApplicationProvider.instance.getSharedPreferences("ADMIN_SP", Context.MODE_PRIVATE)
        .getString("sp_admin_pass", "123")
    return Pair(login, pass)
}

fun setPairLoginAdmin(pair: Pair<String, String>) {
    ApplicationProvider.instance.getSharedPreferences("ADMIN_SP", Context.MODE_PRIVATE)
        .edit()
        .putString("sp_admin_login", pair.first)
        .apply()
    ApplicationProvider.instance.getSharedPreferences("ADMIN_SP", Context.MODE_PRIVATE)
        .edit()
        .putString("sp_admin_pass", pair.second)
        .apply()
}