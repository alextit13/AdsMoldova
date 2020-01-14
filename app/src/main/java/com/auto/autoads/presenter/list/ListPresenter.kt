package com.auto.autoads.presenter.list

import com.auto.autoads.model.server.IListAdListener
import com.auto.autoads.model.utils.Ad
import com.auto.autoads.view.list.IListActivity

class ListPresenter: IListPresenter, IListAdListener {

    private var view: IListActivity? = null

    override fun onViewAttach(view: IListActivity) {
        this.view = view

        initList()
    }

    private fun initList() {

    }

    override fun onListAdsResult(list: List<Ad>) {
        view?.onInitAdapter(list)
    }

    override fun onViewDetach() {
        view = null
    }
}