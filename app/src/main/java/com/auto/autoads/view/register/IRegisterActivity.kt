package com.auto.autoads.view.register

interface IRegisterActivity {
    fun initListeners()
    fun dismissProgress(error: String)
    fun showProgress()
    fun openMainActivity()
    fun showDialogCodeSend(message: String, callback: () -> Unit)
    fun openConfirmActivity()
    fun showToastMessage(message: String)
}