package com.auto.autoads.view.search.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.ad.AdComplexSearchManager
import com.auto.autoads.model.db.DatabaseEntry
import com.auto.autoads.model.db.DatabaseEntry.getForCarTypes
import com.auto.autoads.model.db.DatabaseEntry.getTypeCategory
import com.auto.autoads.view.search.SearchAdResultCallback
import com.auto.autoads.view.search.SearchResultActivity
import kotlinx.android.synthetic.main.activity_search_parts.*

class SearchParts : AppCompatActivity(), SearchAdResultCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_parts)

        spinnerCategory.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getTypeCategory()
            )
        spinnerType.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getForCarTypes()
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
            "Запчасти",
            spinnerCategory.selectedItem.toString(),
            spinnerType.selectedItem.toString(),
            "",
            0,
            Int.MAX_VALUE,
            "",
            0,
            Int.MAX_VALUE,
            if (etPriceFrom.text.toString() == "") 0 else etPriceFrom.text.toString().toInt(),
            if (etPriceTo.text.toString() == "") Integer.MAX_VALUE else etPriceTo.text.toString().toInt(),
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