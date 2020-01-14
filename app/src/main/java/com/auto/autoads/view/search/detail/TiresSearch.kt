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
import com.auto.autoads.model.utils.makeDef
import com.auto.autoads.view.search.SearchAdResultCallback
import com.auto.autoads.view.search.SearchResultActivity
import kotlinx.android.synthetic.main.activity_add_ad_tires.spinnerCategory
import kotlinx.android.synthetic.main.activity_add_ad_tires.spinnerLocation
import kotlinx.android.synthetic.main.activity_search_moto.price_from
import kotlinx.android.synthetic.main.activity_search_moto.price_to

class TiresSearch : AppCompatActivity(), SearchAdResultCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_tires)

        spinnerCategory.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getTypeCategory()
            )
        /*spinnerSeasons.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getSiasons()
            )
        spinnerConsist.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getTiresConsist()
            )
        spinnerForCars.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getForCarTypes()
            )*/
        spinnerLocation.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getLocation()
            )
    }

    fun cancel(view: View) {
        finish()
    }

    fun search(view: View) {
        AdComplexSearchManager.complexSearch(
            this,
            "Шины",
            spinnerCategory.selectedItem.toString(),
            "",
            "",
            0,
            Int.MAX_VALUE,
            "",
            0,
            Int.MAX_VALUE,
            price_from.text.makeDef().toInt(),
            price_to.text.makeDef().toInt(),
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
            Integer.MAX_VALUE,
            0,
            Integer.MAX_VALUE,
            0,
            Integer.MAX_VALUE,
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

    override fun onSearchResult() {
        startActivity(Intent(this, SearchResultActivity::class.java).putExtra("simpleOrComplex", 2))
    }

    override fun onError() {
        Toast.makeText(this, "Ничего не найдено", Toast.LENGTH_SHORT).show()
    }
}