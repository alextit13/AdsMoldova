package com.auto.autoads.presenter.main

import com.auto.autoads.view.main.IMainView

interface IMainPresenter {
    fun onViewAttach(view: IMainView)
    fun onViewDetach()
    fun onClickExtensionSearch()
    fun onOptionsItemSelected(itemId: Int?)
    fun onClickAddAd()
}