package com.auto.autoads.presenter.list

import com.auto.autoads.view.list.IListActivity

interface IListPresenter {
    fun onViewAttach(view: IListActivity)
    fun onViewDetach()
}