package com.auto.autoads.view.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.auto.autoads.R
import com.auto.autoads.model.ad.AdManager
import com.auto.autoads.model.ad.AdManager.listSimpleSearchResult
import com.auto.autoads.model.ad.ISimpleSearchListener
import com.auto.autoads.model.ad.compareByDate
import com.auto.autoads.model.utils.Ad
import com.auto.autoads.view.detail.DetailActivity
import com.auto.autoads.view.dialog.showFavoritDialog
import com.auto.autoads.view.list.IListItemClickListener
import com.auto.autoads.view.list.ListResultAdapter
import kotlinx.android.synthetic.main.activity_search_result.*

class SearchResultActivity : AppCompatActivity(), ISimpleSearchListener, IListItemClickListener {
    companion object {
        var s: String? = null
    }

    private var adapter: ListResultAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        flProgress.visibility = View.VISIBLE

        when (intent.getIntExtra("simpleOrComplex", 1)) {
            1 -> {
                AdManager.getSimpleSearchAds(s ?: "", this)
            }
            2 -> {
                onSearchSimpleResult()
            }
        }
    }

    override fun onSearchSimpleResult() {
        if (adapter == null) {
            adapter = ListResultAdapter(listSimpleSearchResult.compareByDate(), this) {
                showFavoritDialog(it, this)
            }
            rvSearchREsult.layoutManager = LinearLayoutManager(this)
            rvSearchREsult.adapter = adapter
        }
        adapter?.notifyDataSetChanged()
        flProgress.visibility = View.GONE
    }

    override fun onError() {
        Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show()
    }

    override fun onItemAdClick(ad: Ad) {
        DetailActivity.ad = ad
        startActivity(Intent(this, DetailActivity::class.java))
    }

    override fun onLongClick(ad: Ad) {

    }
}
