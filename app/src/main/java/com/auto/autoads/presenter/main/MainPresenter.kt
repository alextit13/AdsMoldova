package com.auto.autoads.presenter.main

import com.auto.autoads.R
import com.auto.autoads.model.image.ImgeManager
import com.auto.autoads.model.utils.Message
import com.auto.autoads.view.main.IMainView
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class MainPresenter : IMainPresenter {

    private var view: IMainView? = null

    override fun onViewAttach(view: IMainView) {
        this.view = view

        view.initListeners()
        ImgeManager.newInstance().getImageLinkForListBaner()

        checkUserInDataHolder()
    }

    private fun checkUserInDataHolder() {
        // if (DataHandler.currentUser == null) view?.finishCurrentActivity()
    }

    override fun onClickExtensionSearch() {
        view?.showListAllAds()// TODO for testing only
    }

    override fun onClickAddAd() {
        view?.openAddAdActivity()
    }

    override fun onOptionsItemSelected(itemId: Int?) {
        when (itemId) {
            R.id.menu_user -> {
                view?.openUserActivity()
            }
            else -> return
        }
    }

    override fun onClickSendMessage(title: String, message: String, name: String, email: String) {
        val msg = Message(
            Date().time.toString(),
            title, message, email, name
        )
        FirebaseDatabase.getInstance().getReference("admin_messages")
            .child(msg.id)
            .setValue(msg)
    }

    override fun onViewDetach() {
        view = null
    }
}