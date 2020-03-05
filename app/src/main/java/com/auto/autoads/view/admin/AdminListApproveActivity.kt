package com.auto.autoads.view.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.ad.AdManager
import com.auto.autoads.model.ad.AdManager.listAdminAds
import com.auto.autoads.model.admin.AdminManager
import com.auto.autoads.model.utils.Ad
import com.auto.autoads.view.detail.DetailActivity
import com.auto.autoads.view.list.IListItemClickListener
import kotlinx.android.synthetic.main.activity_admin_list_approve.*

class AdminListApproveActivity : AppCompatActivity(), IListItemClickListener, ILongClickListener,
    IAdsAdminResult {

    var adapter: AdminAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_list_approve)
        AdManager.getAllAds(this)
    }

    override fun onItemAdClick(ad: Ad) {
        DetailActivity.ad = ad
        startActivity(Intent(this, DetailActivity::class.java))
    }

    override fun allAds() {
        if (adapter == null) {
            adapter = AdminAdapter(
                listAdminAds.reversed().toMutableList(), {
                    onItemAdClick(it)
                }, {
                    onLongClick(it)
                })
            rvApproveAds.adapter = adapter
        } else {
            adapter?.list = listAdminAds
            adapter?.notifyDataSetChanged()
        }
    }

    private var isForApprove = false

    override fun onLongClick(ad: Ad) {
        AlertDialog.Builder(this)
            .setPositiveButton("Одобрить объявление") { p0, _ ->
                isForApprove = true
                AdminManager.onApproveAd(ad, this)
                p0?.dismiss()
            }
            .setNegativeButton(
                "В премиум"
            ) { p0, _ ->
                AdminManager.makeAdAsForward(ad)
                p0?.dismiss()
            }
            .setNeutralButton("Удалить") { a, _ ->
                AdminManager.deleteAd(ad, {
                    Toast.makeText(
                        this,
                        "Успешно удалено",
                        Toast.LENGTH_LONG
                    ).show()
                }, this)
                a.dismiss()
            }.show()
    }
}