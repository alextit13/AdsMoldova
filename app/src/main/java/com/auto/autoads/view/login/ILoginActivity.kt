package com.auto.autoads.view.login

interface ILoginActivity {
    fun initListeners()
    fun dismissProgress(error: String)
    fun onLoginSuccess()
    fun showProgress()
    fun openAdminMenu()
}