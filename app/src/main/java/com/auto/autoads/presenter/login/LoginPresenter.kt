package com.auto.autoads.presenter.login

import com.auto.autoads.R
import com.auto.autoads.model.ApplicationProvider
import com.auto.autoads.model.SpManager
import com.auto.autoads.model.db.getPairLoginAdmin
import com.auto.autoads.model.login.ILoginListener
import com.auto.autoads.model.login.LoginManager
import com.auto.autoads.model.utils.DataHandler.currentUser
import com.auto.autoads.model.utils.User
import com.auto.autoads.view.login.ILoginActivity
import java.util.*

class LoginPresenter : ILoginPresenter, ILoginListener {

    private var view: ILoginActivity? = null

    override fun onViewAttach(view: ILoginActivity) {
        this.view = view

        view.initListeners()

        checkUserInDataHolder()
        checkUserRegCode()
    }

    private fun checkUserInDataHolder() {
        if (currentUser == null) return

        onClickLogin(
            currentUser?.email ?: "",
            currentUser?.password ?: ""
        )
    }

    override fun checkUserRegCode() {
        /*val code = SpManager.getUserRegCode()
        if (code != "") {
            view?.openConfirmCodeActivity()
        }*/
    }

    override fun onClickResetPass(email: String) {
        if (email == "") {
            view?.showToastMessage("Вы не ввели Email")
            return
        }
        LoginManager.changeUserPass(email, {
            view?.showToastMessage(it)
        }, {
            view?.showToastMessage(it)
        })
    }

    override fun onClickLogin(email: String, password: String) {
        val dataAdmin = getPairLoginAdmin()
        val adminLogin = dataAdmin.first
        val adminPass = dataAdmin.second

        if (email == adminLogin && password == adminPass) {
            view?.openAdminMenu()
        }
        view?.showProgress()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            LoginManager.loginUser(email, password, this)
        } else {
            view?.dismissProgress(ApplicationProvider.instance.getString(R.string.complete_all_fields))
        }
    }

    override fun onLoginSuccess(user: User) {
        currentUser = user
        view?.apply {
            dismissProgress("")
            onLoginSuccess()
        }
    }

    override fun onLoginError(error: String) {
        view?.dismissProgress(error)
    }

    override fun onViewDetach() {
        view = null
    }

    companion object {
        private val ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm"
    }
}