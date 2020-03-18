package com.auto.autoads.view.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.presenter.register.IRegisterPresenter
import com.auto.autoads.presenter.register.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), IRegisterActivity {

    private var presenter: IRegisterPresenter? = null

    override fun onResume() {
        super.onResume()

        presenter = RegisterPresenter()
        presenter?.onViewAttach(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    override fun initListeners() {
        btnCancel.setOnClickListener { finish() }
        btnConfirm.setOnClickListener {
            presenter?.onClickRegister(
                etEmail.text.toString(),
                etPassword.text.toString(),
                etConfirmPassword.text.toString()
            )
        }
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun closeCurrentScreen() {
        finish()
    }

    override fun openConfirmActivity() {
        startActivity(Intent(this, ConfirmCodeActivity::class.java))
    }

    override fun showDialogCodeSend(
        message: String,
        callback: () -> Unit
    ) {
        AlertDialog.Builder(this)
            .setTitle("Подтверждение")
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("Ок") { d, _ ->
                callback.invoke()
                d.dismiss()
            }.create().show()
    }

    override fun showProgress() {
        progressRegister.visibility = View.VISIBLE
        rlContainerRegister.visibility = View.GONE
    }

    override fun dismissProgress(error: String) {
        if (error.isNotEmpty()) Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        progressRegister.visibility = View.GONE
        rlContainerRegister.visibility = View.VISIBLE
        finish()
    }

    override fun openMainActivity() {
        Toast.makeText(
            this, "Успешно зарегистрированы! Войдите в свой профиль",
            Toast.LENGTH_LONG
        ).show()
        finish()
    }

    override fun onStop() {
        presenter?.onViewDetach()
        presenter = null
        super.onStop()
    }
}