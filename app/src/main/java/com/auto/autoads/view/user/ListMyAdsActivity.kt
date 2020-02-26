package com.auto.autoads.view.user

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.ad.AdManager
import com.auto.autoads.model.ad.AdManager.listAds
import com.auto.autoads.model.image.ImgeManager
import com.auto.autoads.model.utils.Ad
import com.auto.autoads.view.detail.DetailActivity
import com.auto.autoads.view.dialog.showFavoritDialog
import com.auto.autoads.view.list.IListItemClickListener
import com.auto.autoads.view.list.ListResultAdapter
import kotlinx.android.synthetic.main.activity_my_ads_list.*

class ListMyAdsActivity : AppCompatActivity(), IDownloadAdsListener, IListItemClickListener {
    private var adapter: ListResultAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_ads_list)

        initAdapter()
        AdManager.getMyAds(this)
    }

    private fun initAdapter() {

    }

    override fun onMyAds() {
        rlListAds.visibility = View.VISIBLE
        flProgress.visibility = View.GONE
        if (adapter == null) {
            adapter = ListResultAdapter(listAds, ImgeManager.bannerInListImageUrl,this) {
                showFavoritDialog(it, this)
            }
            rlListAds.adapter = adapter
        }
        adapter?.notifyDataSetChanged()
    }

    override fun onLongClick(ad: Ad) {
        AlertDialog.Builder(this)
            .setTitle("Объявление")
            .setMessage("Хотите удалить объявление?")
            .setPositiveButton("Да") { p0, _ ->
                p0?.dismiss()
                AdManager.deleteAd(ad)
            }
            .setNegativeButton(
                "Отмена"
            ) { p0, _ -> p0?.dismiss() }
            .show()
    }

    override fun empty() {
        runOnUiThread {
            Toast.makeText(this, "У Вас нет объявлений", Toast.LENGTH_LONG).show()
            rlListAds.visibility = View.VISIBLE
            flProgress.visibility = View.GONE
        }
    }

    override fun onItemAdClick(ad: Ad) {
        DetailActivity.ad = ad
        startActivity(Intent(this, DetailActivity::class.java))
    }

    override fun error() {
        Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show()
        rlListAds.visibility = View.VISIBLE
        flProgress.visibility = View.GONE
    }
}