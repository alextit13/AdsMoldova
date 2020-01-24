package com.auto.autoads.view.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.presenter.login.ILoginPresenter
import com.auto.autoads.presenter.login.LoginPresenter
import com.auto.autoads.view.admin.AdminActivity
import com.auto.autoads.view.main.MainActivity
import com.auto.autoads.view.register.ConfirmCodeActivity
import com.auto.autoads.view.register.License
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), ILoginActivity {

    private var presenter: ILoginPresenter? = null

    override fun onResume() {
        super.onResume()

        presenter = LoginPresenter()
        presenter?.onViewAttach(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun openConfirmCodeActivity() {
        startActivity(Intent(this, ConfirmCodeActivity::class.java))
    }

    override fun initListeners() {
        btnCancel.setOnClickListener { finish() }
        btnLogin.setOnClickListener {
            presenter?.onClickLogin(
                etLogin.text.toString(),
                etPassword.text.toString()
            )
        }
    }

    override fun showProgress() {
        rlContainerLogin.visibility = View.GONE
        flProgressLogin.visibility = View.VISIBLE
    }

    override fun onLoginSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun openAdminMenu() {
        startActivity(Intent(this, AdminActivity::class.java))
    }

    override fun dismissProgress(error: String) {
        if (error.isNotEmpty()) Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        rlContainerLogin.visibility = View.VISIBLE
        flProgressLogin.visibility = View.GONE
    }

    override fun onStop() {
        presenter?.onViewDetach()
        presenter = null
        super.onStop()
    }

    fun registration(view: View) {
        startActivity(Intent(this, License::class.java))
    }

    fun recreatePass(v: View) {
        val input = EditText(this)

        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)

        input.layoutParams = lp

        AlertDialog.Builder(this)
            .setView(input)
            .setTitle("Пароль")
            .setMessage("Новый пароль будет отправлен на указаную Вами почту")
            .setPositiveButton("Сбросить") { d, _ ->
                presenter?.onClickResetPass(input.text.toString())
                d.dismiss()
            }
            .setNegativeButton("Отмена") { d, _ ->
                d.dismiss()
            }.create().show()
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}