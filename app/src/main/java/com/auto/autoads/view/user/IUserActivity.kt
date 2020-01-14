package com.auto.autoads.view.user

interface IUserActivity {
    fun initListeners()
    fun setUserLogin(login: String?)
    fun setUserEmail(email: String?)
    fun finishCurrentActivity()
}