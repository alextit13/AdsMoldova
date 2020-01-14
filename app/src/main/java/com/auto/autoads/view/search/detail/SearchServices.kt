package com.auto.autoads.view.search.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.ad.AdComplexSearchManager
import com.auto.autoads.model.ad.AdManager
import com.auto.autoads.model.db.DatabaseEntry
import com.auto.autoads.view.search.SearchAdResultCallback
import com.auto.autoads.view.search.SearchResultActivity
import kotlinx.android.synthetic.main.activity_search_services.*

class SearchServices : AppCompatActivity(), SearchAdResultCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_services)

        spinnerCategory.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getCategoriesServices()
            )
        spinnerLocation.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getLocation()
            )
    }

    fun search(view: View) {
        AdComplexSearchManager.complexSearch(
            this,
            "Авто услуги",
            spinnerCategory.selectedItem.toString(),
            "",
            "",
            0,
            Int.MAX_VALUE,
            "",
            0,
            Int.MAX_VALUE,
            0,
            Int.MAX_VALUE,
            "",
            "",
            0,
            Int.MAX_VALUE,
            "",
            "",
            "",
            "",
            0,
            Int.MAX_VALUE,
            "",
            spinnerLocation.selectedItem.toString(),
            false,
            0,
            Int.MAX_VALUE,
            0,
            Int.MAX_VALUE,
            0,
            Int.MAX_VALUE,
            0,
            Int.MAX_VALUE,
            "",
            "",
            "",
            "",
            "",
            ""
        )
    }

    fun cancelSearch(view: View) {
        finish()
    }

    override fun onSearchResult() {
        startActivity(Intent(this, SearchResultActivity::class.java).putExtra("simpleOrComplex", 2))
    }

    override fun onError() {
        Toast.makeText(this, "Ничего не найдено", Toast.LENGTH_SHORT).show()
    }
}