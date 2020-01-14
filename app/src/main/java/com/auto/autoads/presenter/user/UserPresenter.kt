package com.auto.autoads.presenter.user

import com.auto.autoads.model.SpManager
import com.auto.autoads.model.utils.DataHandler
import com.auto.autoads.view.user.IUserActivity

class UserPresenter: IUserPresenter {

    private var view: IUserActivity? = null

    override fun onViewAttach(view: IUserActivity) {
        this.view = view

        view.apply {
            initListeners()
            setUserLogin(DataHandler.currentUser?.login)
            setUserEmail(DataHandler.currentUser?.email)
        }
    }

    override fun onClickExitFromAccount() {
        SpManager.deleteUser()
        DataHandler.currentUser = null
        view?.finishCurrentActivity()
    }

    override fun onViewDetach() {
        view = null
    }
}