package com.auto.autoads.presenter.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.auto.autoads.R
import kotlinx.android.synthetic.main.dialog_connect.*

class ConnectDialog(
    private val callback: (String, String, String, String) -> Unit
) : DialogFragment() {

    var titleEt: EditText? = null
    var messageEt: EditText? = null
    var nameEt: EditText? = null
    var emailEt: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(
            R.layout.dialog_connect, container, false
        )
        titleEt = rootView.findViewById(R.id.etTheme) as EditText
        messageEt = rootView.findViewById(R.id.etBody) as EditText
        nameEt = rootView.findViewById(R.id.etName) as EditText
        emailEt = rootView.findViewById(R.id.etPost) as EditText
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickers()
    }

    private fun initClickers() {
        btnSend.setOnClickListener {
            val title = titleEt?.text.toString()
            val message = messageEt?.text.toString()
            val name = nameEt?.text.toString()
            val email = emailEt?.text.toString()
            if (title != "" && message != "" && name != "" && email != "") {
                callback.invoke(title, message, name, email)
            } else {
                Toast.makeText(context, "Заволните все поля", Toast.LENGTH_LONG).show()
            }
        }
    }
}