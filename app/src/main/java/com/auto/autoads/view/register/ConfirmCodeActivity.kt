package com.auto.autoads.view.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.SpManager
import com.auto.autoads.view.login.LoginActivity
import com.auto.autoads.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_confirm_code.*

class ConfirmCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_code)

        btnConfirm.setOnClickListener {
            val code = etCheckCode.text.toString()
            codeWasEnter(code)
        }
        btnCancel.setOnClickListener { startActivity(
            Intent(this, MainActivity::class.java)
        ) }
    }

    private fun codeWasEnter(code: String) {
        if (code == "") {
            Toast.makeText(this, "Введите код", Toast.LENGTH_LONG).show()
            return
        }

        val codeFromSp = SpManager.getUserRegCode()
        if (code == codeFromSp) {
            Toast.makeText(
                this,
                "Вы успешно зарегистрированы!", Toast.LENGTH_LONG
            ).show()
            SpManager.setUserRegCode("")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(
                this,
                "Неверный код, попробуйте еще раз", Toast.LENGTH_LONG
            ).show()
            return
        }
    }
}