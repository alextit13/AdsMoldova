package com.auto.autoads.presenter.register

import com.auto.autoads.view.register.IRegisterActivity

interface IRegisterPresenter {
    fun onViewAttach(view: IRegisterActivity)
    fun onViewDetach()
    fun onClickRegister(email: String, password: String, confirmPassword: String)
}