package com.auto.autoads.view.favorits

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.ad.FavoritAdManager
import com.auto.autoads.model.db.DBGate
import com.auto.autoads.model.utils.Ad
import com.auto.autoads.presenter.favorits.FavoritsPresenter
import com.auto.autoads.view.detail.DetailActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_favorits.*

class FavoritsActivity : AppCompatActivity() {

    private var presenter: FavoritsPresenter? = null
    private var adapter: FavoritAdpter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorits)
    }

    override fun onResume() {
        super.onResume()

        presenter = FavoritsPresenter()
        presenter?.onViewAttach(this)
    }

    fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun initAdapter(items: MutableList<Ad>) {
        if (adapter == null) {
            adapter = FavoritAdpter(items, {
                DetailActivity.ad = it
                startActivity(Intent(this, DetailActivity::class.java))
            }, {
                deleteFromFavoritAlertDialog(it)
            })
            rvFavorit.adapter = adapter
        } else {
            adapter?.items = items
            rvFavorit.adapter?.notifyDataSetChanged()
        }
    }

    private fun deleteFromFavoritAlertDialog(ad: Ad) {
        AlertDialog.Builder(this)
            .setTitle("Избранное")
            .setMessage("Удалить из избранного?")
            .setPositiveButton("Удалить") { a, _ ->
                deleteAd(ad)
                a.dismiss()
            }
            .setNegativeButton("Отмена") { a, _ ->
                a.dismiss()
            }
            .create().show()
    }

    private fun deleteAd(ad: Ad) {
        // DBGate.newInstance().delete(Gson().toJson(ad))
        FavoritAdManager.removeAdFromFavorit(ad.id ?: 0L, {
            showToastMessage(it)
        }, {
            showToastMessage(it)
        })

        adapter = null
        presenter?.refreshAdapter()
    }

    override fun onPause() {
        presenter?.onViewDetach()
        presenter = null
        super.onPause()
    }
}