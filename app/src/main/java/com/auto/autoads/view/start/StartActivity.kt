package com.auto.autoads.view.start

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.ad.AdManager.sendAdToServer
import com.auto.autoads.model.utils.Ad
import com.auto.autoads.presenter.start.IStartPresenter
import com.auto.autoads.presenter.start.StartPresenter
import com.auto.autoads.view.login.LoginActivity
import com.auto.autoads.view.register.License
import kotlinx.android.synthetic.main.activity_start.*
import java.util.*

class StartActivity : AppCompatActivity(), IStartView {

    private var presenter: IStartPresenter? = null

    override fun onResume() {
        super.onResume()

        presenter = StartPresenter()
        presenter?.onViewAttach(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }

    override fun initListeners() {
        btnLogin.setOnClickListener { presenter?.onClickLogin() }
        btnRegister.setOnClickListener { presenter?.onClickRegister() }
    }

    override fun openLoginView() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun openRegicterView() {
        startActivity(Intent(this, License::class.java))
    }

    override fun onStop() {
        presenter?.onViewDetach()
        presenter = null
        super.onStop()
    }
}