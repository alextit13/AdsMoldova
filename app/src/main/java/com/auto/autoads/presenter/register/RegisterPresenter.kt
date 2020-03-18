package com.auto.autoads.presenter.register

import com.auto.autoads.R
import com.auto.autoads.model.ApplicationProvider
import com.auto.autoads.model.SpManager
import com.auto.autoads.model.login.EmailSender
import com.auto.autoads.model.login.IRegisterListener
import com.auto.autoads.model.login.LoginManager
import com.auto.autoads.model.utils.User
import com.auto.autoads.view.register.IRegisterActivity

class RegisterPresenter : IRegisterPresenter, IRegisterListener {

    private var view: IRegisterActivity? = null

    override fun onViewAttach(view: IRegisterActivity) {
        this.view = view

        view.initListeners()
    }

    override fun onClickRegister(email: String, password: String, confirmPassword: String) {
        view?.showProgress()
        if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
            if (password == confirmPassword) {
                if (password.length > 6) {
                    LoginManager.registerUser(email, password, this)
                } else {
                    onRegisterError("Пароль должен содержать более 6 символов")
                }
            } else {
                onRegisterError(ApplicationProvider.instance.getString(R.string.password_not_equals))
            }
        } else {
            onRegisterError(ApplicationProvider.instance.getString(R.string.complete_all_fields))
        }
    }

    private fun sendCodeToEmail(email: String) {
        val code = getRandomCode()
        SpManager.setUserRegCode(code)
        sendCode(code, email, {
            view?.showDialogCodeSend("Код был отправлен на указанный Вами Email.") {
                view?.openConfirmActivity()
            }
        }, {
            view?.closeCurrentScreen()
            view?.showToastMessage("Ошибка при отправке кода. Попробуйте позже")
        })
    }

    private fun getRandomCode(): String = (9_999..99_999_999).random().toString()

    private fun sendCode(
        code: String,
        email: String,
        callback: (String) -> Unit,
        error: (String) -> Unit
    ) {
        EmailSender.sendEmailCode(
            code, email, {
                callback.invoke(it)
            }, {
                error.invoke(it)
            }
        )
    }

    override fun onRegisterSuccess(user: User) {
        sendCodeToEmail(user.email)
    }

    override fun onRegisterError(error: String) {
        view?.dismissProgress(error)
    }

    override fun onViewDetach() {
        view = null
    }
}