package com.auto.autoads.presenter.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.auto.autoads.R
import com.auto.autoads.model.db.LocaleDbHolder
import com.auto.autoads.model.utils.Connect
import kotlinx.android.synthetic.main.dialog_connect.*

class ConnectDialog(
    private val callback: (Connect) -> Unit
) : DialogFragment() {

    private var adapter: ConnectAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.dialog_connect, container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }

    private fun initAdapter() {
        if (adapter == null) {
            adapter = ConnectAdapter(
                LocaleDbHolder.getConnectData()
            ) {
                callback.invoke(it)
            }
        } else {
            adapter?.connectVariant = LocaleDbHolder.getConnectData()
            rvConnect.adapter?.notifyDataSetChanged()
        }
        rvConnect.adapter = adapter
    }
}