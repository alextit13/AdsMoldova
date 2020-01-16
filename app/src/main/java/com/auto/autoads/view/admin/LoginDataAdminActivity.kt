package com.auto.autoads.view.admin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.db.getPairLoginAdmin
import com.auto.autoads.model.db.setPairLoginAdmin
import kotlinx.android.synthetic.main.activity_login_admin.*

class LoginDataAdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login_admin)

        val data = getPairLoginAdmin()
        etLoginAdmin.setText(data.first)
        etPassAdmin.setText(data.second)
    }

    fun save(v: View) {
        val login = etLoginAdmin.text.toString()
        val pass = etPassAdmin.text.toString()

        if (login == "" || pass == "") {
            Toast.makeText(this, "Введите данные", Toast.LENGTH_LONG).show()
            return
        }

        val data = Pair(login, pass)

        setPairLoginAdmin(data)
        Toast.makeText(this, "Данные успешно изменены", Toast.LENGTH_LONG).show()
        finish()
    }

    fun cancel(v: View) {
        finish()
    }
}