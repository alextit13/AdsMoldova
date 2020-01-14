package com.auto.autoads.presenter.user

import com.auto.autoads.view.user.IUserActivity

interface IUserPresenter {
    fun onViewAttach(view: IUserActivity)
    fun onViewDetach()
    fun onClickExitFromAccount()
}