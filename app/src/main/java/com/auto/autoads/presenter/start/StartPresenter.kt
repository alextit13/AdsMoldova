package com.auto.autoads.presenter.start

import com.auto.autoads.model.SpManager
import com.auto.autoads.model.utils.DataHandler
import com.auto.autoads.view.start.IStartView

class StartPresenter: IStartPresenter {

    private var view: IStartView? = null

    override fun onViewAttach(view: IStartView) {
        this.view = view

        view.initListeners()

        checkSp()


    }

    private fun checkSp() {
        val user = SpManager.getUser() ?: return

        DataHandler.currentUser = user
        view?.openLoginView()
    }

    override fun onClickLogin() {
        view?.openLoginView()
    }

    override fun onClickRegister() {
        view?.openRegicterView()
    }

    override fun onViewDetach() {
        view = null
    }
}