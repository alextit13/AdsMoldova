package com.auto.autoads.presenter.user

import com.auto.autoads.model.ApplicationProvider
import com.auto.autoads.model.SpManager
import com.auto.autoads.model.db.DBGate
import com.auto.autoads.model.db.DbHelper
import com.auto.autoads.model.login.LoginManager
import com.auto.autoads.model.utils.DataHandler
import com.auto.autoads.view.user.IUserActivity
import com.google.gson.Gson

class UserPresenter : IUserPresenter {

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
        deleteAllFavorits()
        DataHandler.currentUser = null
        view?.finishCurrentActivity()
    }

    private fun deleteAllFavorits() {
        /*val allItems = DBGate.newInstance().getAll()
        for (ad in allItems) {
            DBGate.newInstance().delete(Gson().toJson(ad))
        }*/
    }

    override fun onViewDetach() {
        view = null
    }
}