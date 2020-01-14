package com.auto.autoads.presenter.start

import com.auto.autoads.view.start.IStartView

interface IStartPresenter {
    fun onViewAttach(view: IStartView)
    fun onViewDetach()
    fun onClickLogin()
    fun onClickRegister()
}