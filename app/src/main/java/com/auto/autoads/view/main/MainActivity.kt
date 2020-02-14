package com.auto.autoads.view.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.auto.autoads.R
import com.auto.autoads.model.SpManager.getUser
import com.auto.autoads.model.ad.AdManager
import com.auto.autoads.model.image.ImgeManager
import com.auto.autoads.model.server.InternetHandler
import com.auto.autoads.model.utils.Connect
import com.auto.autoads.presenter.main.ConnectDialog
import com.auto.autoads.presenter.main.IMainPresenter
import com.auto.autoads.presenter.main.MainPresenter
import com.auto.autoads.view.add.AddAdActivity
import com.auto.autoads.view.detail.DetailActivity
import com.auto.autoads.view.login.LoginActivity
import com.auto.autoads.view.search.DetailSearchActivity
import com.auto.autoads.view.search.SearchResultActivity
import com.auto.autoads.view.user.UserActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMainView, IListFavorits {

    private var presenter: IMainPresenter? = null
    private var imgManager: ImgeManager? = null

    override fun onResume() {
        super.onResume()

        presenter = MainPresenter()
        presenter?.onViewAttach(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        InternetHandler.startCheckInternetConnection()
        checkAllPermissions()
    }

    private fun checkAllPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ), 332
            )
        }
    }

    private fun getImgManagerInstance(): ImgeManager {
        if (imgManager == null)
            imgManager = ImgeManager.newInstance()
        return imgManager as ImgeManager
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
                val intent = Intent(this, SearchResultActivity::class.java)
                intent.putExtra("simpleOrComplex", 1) // simple = 1, complex =2
                intent.putExtra("enterQuery", etSearch.text.toString()) // simple = 1, complex =2
                startActivity(intent)
            }
            true
        }

        getImgManagerInstance().getImageTopBanner(this, flAdBannerTop)
        getImgManagerInstance().getImageBottomBanner(flAdBannerBottom, this)

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
        initBanners()
    }

    private fun initBanners() {
        if (AdManager.listFavorits.isEmpty()) return
        for (i in 0 until AdManager.listFavorits.size) {
            try {
                val ad = AdManager.listFavorits[i]
                when (i) {
                    0 -> {
                        rl_1.setOnClickListener {
                            DetailActivity.ad = ad
                            startActivity(Intent(this, DetailActivity::class.java))
                        }
                        title_ad_1.text = ad.category
                        if (ad.linkImages?.isNotEmpty() == true)
                            Picasso.get().load(ad.linkImages?.first()).resize(TARGET_SIZE, TARGET_SIZE).into(iv_ad_1)

                    }
                    1 -> {
                        rl_2.setOnClickListener {
                            DetailActivity.ad = ad
                            startActivity(Intent(this, DetailActivity::class.java))
                        }
                        title_ad_2.text = ad.category
                        if (ad.linkImages?.isNotEmpty() == true)
                            Picasso.get().load(ad.linkImages?.first()).resize(TARGET_SIZE, TARGET_SIZE).into(iv_ad_2)
                    }
                    2 -> {
                        rl_3.setOnClickListener {
                            DetailActivity.ad = ad
                            startActivity(Intent(this, DetailActivity::class.java))
                        }
                        title_ad_3.text = ad.category
                        if (ad.linkImages?.isNotEmpty() == true)
                            Picasso.get().load(ad.linkImages?.first()).resize(TARGET_SIZE, TARGET_SIZE).into(iv_ad_3)
                    }
                    3 -> {
                        rl_4.setOnClickListener {
                            DetailActivity.ad = ad
                            startActivity(Intent(this, DetailActivity::class.java))
                        }
                        title_ad_4.text = ad.category
                        if (ad.linkImages?.isNotEmpty() == true)
                            Picasso.get().load(ad.linkImages?.first()).resize(TARGET_SIZE, TARGET_SIZE).into(iv_ad_4)
                    }
                    4 -> {
                        rl_5.setOnClickListener {
                            DetailActivity.ad = ad
                            startActivity(Intent(this, DetailActivity::class.java))
                        }
                        title_ad_5.text = ad.category
                        if (ad.linkImages?.isNotEmpty() == true)
                            Picasso.get().load(ad.linkImages?.first()).resize(TARGET_SIZE, TARGET_SIZE).into(iv_ad_5)
                    }
                    5 -> {
                        rl_6.setOnClickListener {
                            DetailActivity.ad = ad
                            startActivity(Intent(this, DetailActivity::class.java))
                        }
                        title_ad_6.text = ad.category
                        if (ad.linkImages?.isNotEmpty() == true)
                            Picasso.get().load(ad.linkImages?.first()).resize(TARGET_SIZE, TARGET_SIZE).into(iv_ad_6)
                    }
                    6 -> {
                        rl_7.setOnClickListener {
                            DetailActivity.ad = ad
                            startActivity(Intent(this, DetailActivity::class.java))
                        }
                        title_ad_7.text = ad.category
                        if (ad.linkImages?.isNotEmpty() == true)
                            Picasso.get().load(ad.linkImages?.first()).resize(TARGET_SIZE, TARGET_SIZE).into(iv_ad_7)
                    }
                    7 -> {
                        rl_8.setOnClickListener {
                            DetailActivity.ad = ad
                            startActivity(Intent(this, DetailActivity::class.java))
                        }
                        title_ad_8.text = ad.category
                        if (ad.linkImages?.isNotEmpty() == true)
                            Picasso.get().load(ad.linkImages?.first()).resize(TARGET_SIZE, TARGET_SIZE).into(iv_ad_8)
                    }
                }

            } catch (e: Exception) {

            }
        }
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
        imgManager = null
        presenter = null
        super.onStop()
    }

    fun connectAdmin(view: View) {
        var dialog: ConnectDialog? = null
        dialog = ConnectDialog {
            onConnectWasChoose(it)
            dialog?.dismiss()
        }
        dialog.show(supportFragmentManager, DIALOG_TAG)
    }

    private fun onConnectWasChoose(connect: Connect) {
        when (connect.id) {
            1 -> {
                // viber
                val uriViber = Uri.parse("tel:" + connect.data)
                val viberIntent = Intent("android.intent.action.VIEW")
                viberIntent.data = uriViber
                startActivity(Intent.createChooser(viberIntent, "Choose"))
            }
            2 -> {
                // whatsapp
                val phoneNumberWithCountryCode = connect.data
                val message = "Добрый день!"

                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(
                            String.format(
                                "https://api.whatsapp.com/send?phone=%s&text=%s",
                                phoneNumberWithCountryCode, message
                            )
                        )
                    )
                )
            }
            3 -> {
                // email
                val i = Intent(Intent.ACTION_SEND)
                i.type = "message/rfc822"
                i.putExtra(Intent.EXTRA_EMAIL, arrayOf(connect.data))
                i.putExtra(Intent.EXTRA_SUBJECT, "Вопрос по приложению")
                i.putExtra(Intent.EXTRA_TEXT, "")
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."))
                } catch (ex: android.content.ActivityNotFoundException) {
                    Toast.makeText(
                        this,
                        "К сожалению, не установлен ни один Email клиент",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }

    fun profile(view: View) {
        if (getUser() == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    companion object {
        private const val DIALOG_TAG = "connect_dialog_tag"
        private const val TARGET_SIZE = 250
    }
}
