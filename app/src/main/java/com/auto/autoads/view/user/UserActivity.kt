package com.auto.autoads.view.user

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.presenter.user.IUserPresenter
import com.auto.autoads.presenter.user.UserPresenter
import com.auto.autoads.view.favorits.FavoritsActivity
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity(), IUserActivity {

    private var presenter: IUserPresenter? = null

    override fun onResume() {
        super.onResume()

        presenter = UserPresenter()
        presenter?.onViewAttach(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
    }

    override fun initListeners() {
        btn_exit.setOnClickListener {
            showDialogLogout()
        }
    }

    private fun showDialogLogout() {
        AlertDialog.Builder(this)
            .setTitle("Аккаунт")
            .setMessage("Выйти из аккаунта на данном устройстве?")
            .setPositiveButton("Да") { p0, _ ->
                p0?.dismiss()
                presenter?.onClickExitFromAccount()
            }
            .setNegativeButton(
                "Нет"
            ) { p0, _ -> p0?.dismiss() }
            .create().show()
    }

    override fun finishCurrentActivity() {
        finish()
    }

    @SuppressLint("SetTextI18n")
    override fun setUserLogin(login: String?) {
        tv_login.text = "Мой логин: $login"
    }

    @SuppressLint("SetTextI18n")
    override fun setUserEmail(email: String?) {
        tv_email.text = "Мой email: $email"
    }

    override fun onStop() {
        presenter?.onViewDetach()
        presenter = null
        super.onStop()
    }

    fun myAds(view: View) {
        startActivity(Intent(this, ListMyAdsActivity::class.java))
    }

    fun rules(view: View) {
        AlertDialog.Builder(this)
            .setTitle("Правила пользования")
            .setMessage(R.string.license)
            .setNegativeButton(
                "Закрыть"
            ) { p0, _ -> p0?.dismiss() }
            .create().show()
    }

    fun favorits(view: View) {
        startActivity(Intent(this, FavoritsActivity::class.java))
    }
}