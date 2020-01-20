package com.auto.autoads.presenter.login

import com.auto.autoads.view.login.ILoginActivity

interface ILoginPresenter {
    fun onViewAttach(view: ILoginActivity)
    fun onViewDetach()
    fun onClickLogin(email: String, password: String)
}