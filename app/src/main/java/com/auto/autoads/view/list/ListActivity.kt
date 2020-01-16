package com.auto.autoads.view.list

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.utils.Ad
import com.auto.autoads.presenter.list.IListPresenter
import com.auto.autoads.presenter.list.ListPresenter
import com.auto.autoads.view.detail.DetailActivity
import com.auto.autoads.view.dialog.showFavoritDialog
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity: AppCompatActivity(), IListActivity, IListItemClickListener {
    override fun onLongClick(ad: Ad) {

    }

    private var presenter: IListPresenter? = null

    private var adapter: ListResultAdapter? = null
    override fun onResume() {
        super.onResume()

        presenter = ListPresenter()
        presenter?.onViewAttach(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }

    override fun onInitAdapter(list: List<Ad>) {
        adapter = ListResultAdapter(list, this) {
            showFavoritDialog(it, this)
        }
        rvResult.adapter = adapter
    }

    override fun onItemAdClick(ad: Ad) {
        DetailActivity.ad = ad
        startActivity(Intent(this, DetailActivity::class.java))
    }

    override fun onStop() {
        presenter?.onViewDetach()
        presenter = null
        super.onStop()
    }
}