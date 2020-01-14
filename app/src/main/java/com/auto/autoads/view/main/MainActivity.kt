package com.auto.autoads.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.SpManager.getUser
import com.auto.autoads.model.ad.AdManager
import com.auto.autoads.model.image.ImgeManager
import com.auto.autoads.model.utils.DataHandler
import com.auto.autoads.presenter.main.IMainPresenter
import com.auto.autoads.presenter.main.MainPresenter
import com.auto.autoads.view.add.AddAdActivity
import com.auto.autoads.view.login.LoginActivity
import com.auto.autoads.view.search.DetailSearchActivity
import com.auto.autoads.view.search.SearchResultActivity
import com.auto.autoads.view.user.UserActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMainView, IListFavorits {

    private var presenter: IMainPresenter? = null

    override fun onResume() {
        super.onResume()

        presenter = MainPresenter()
        presenter?.onViewAttach(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    override fun initListeners() {
        btnSearchExtension.setOnClickListener {
            val intent = Intent(this, AddAdActivity::class.java)
            intent.putExtra("target", 2) // 1 - add, 2 - search
            startActivity(intent)
        }
        btnAddAd.setOnClickListener { presenter?.onClickAddAd() }
        etSearch.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                SearchResultActivity.s = etSearch.text.toString()
                val intent = Intent(this, SearchResultActivity::class.java)
                intent.putExtra("simpleOrComplex", 1) // simple = 1, complex =2
                startActivity(intent)
            }
            true
        }

        ImgeManager.getImageTopBanner(flAdBannerTop)
        ImgeManager.getImageBottomBanner(flAdBannerBottom)

        AdManager.getFavoritAds(this)
    }

    override fun showListAllAds() {
        startActivity(Intent(this, DetailSearchActivity::class.java))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.munu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        presenter?.onOptionsItemSelected(item?.itemId)
        return true
    }

    override fun onFavoritsResult() {
        initBanners(this)
    }

    override fun openUserActivity() {
        if (getUser() == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    override fun openAddAdActivity() {
        if (getUser() == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            val intent = Intent(this, AddAdActivity::class.java)
            intent.putExtra("target", 1) // 1 - add, 2 - search
            startActivity(intent)
        }
    }

    override fun finishCurrentActivity() {
        finish()
    }

    override fun onStop() {
        presenter?.onViewDetach()
        presenter = null
        super.onStop()
    }

    fun connectAdmin(view: View) {
        Toast.makeText(this, "Функция в разработке", Toast.LENGTH_SHORT).show()
    }

    fun profile(view: View) {
        if (getUser() == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }
}
