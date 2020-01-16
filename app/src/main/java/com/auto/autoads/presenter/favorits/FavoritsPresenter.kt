package com.auto.autoads.presenter.favorits

import com.auto.autoads.model.db.DBGate
import com.auto.autoads.view.favorits.FavoritsActivity

class FavoritsPresenter {

    private var view: FavoritsActivity? = null

    fun onViewAttach(view: FavoritsActivity) {
        this.view = view
        getDataFromDb()
    }

    private fun getDataFromDb() {
        val list = DBGate.newInstance().getAll()
        view?.initAdapter(list)
    }

    fun onViewDetach() {
        view = null
    }

    fun refreshAdapter() {
        getDataFromDb()
    }
}