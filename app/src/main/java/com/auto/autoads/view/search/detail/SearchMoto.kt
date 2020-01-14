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
import com.auto.autoads.model.db.DatabaseEntry.getCategories
import com.auto.autoads.model.db.DatabaseEntry.getConsist
import com.auto.autoads.model.db.DatabaseEntry.getFuel
import com.auto.autoads.model.db.DatabaseEntry.getLocation
import com.auto.autoads.model.db.DatabaseEntry.getMotoMarks
import com.auto.autoads.model.db.DatabaseEntry.getTypeCooling
import com.auto.autoads.model.db.DatabaseEntry.getTypeEngine
import com.auto.autoads.model.db.DatabaseEntry.getTypeMoto
import com.auto.autoads.model.db.DatabaseEntry.getcountryRegistration
import com.auto.autoads.model.utils.makeDef
import com.auto.autoads.view.search.SearchAdResultCallback
import com.auto.autoads.view.search.SearchResultActivity
import kotlinx.android.synthetic.main.activity_search_moto.*

class SearchMoto : AppCompatActivity(), SearchAdResultCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_moto)

        spinnerCategory.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getCategories()
            )
        spinnerMarka.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getMotoMarks()
            )
        spinnerTypeFuel.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getFuel()
            )
        spinnerRegistrationCountry.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getcountryRegistration()
            )
        spinnerTypeBody.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getTypeMoto()
            )
        spinnerCooler.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getTypeCooling()
            )
        spinnerTypeEngine.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getTypeEngine()
            )
        spinnerConsist.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getConsist()
            )
        spinnerLocation.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getLocation()
            )
    }

    fun cancel(view: View) {
        finish()
    }

    fun search(view: View) {
        AdComplexSearchManager.complexSearch(
            this,
            "Мотоциклы и мототехника",
            spinnerCategory.selectedItem.toString(),
            spinnerMarka.selectedItem.toString(),
            "",
            year_from.text.makeDef().toInt(),
            year_to.text.makeDef().toInt(),
            spinnerTypeFuel.selectedItem.toString(),
            0,
            Int.MAX_VALUE,
            price_from.text.makeDef().toInt(),
            price_to.text.makeDef().toInt(),
            spinnerRegistrationCountry.selectedItem.toString(),
            "",
            0,
            Int.MAX_VALUE,
            "",
            "",
            "",
            "",
            0,
            Int.MAX_VALUE,
            spinnerConsist.selectedItem.toString(),
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
            spinnerTypeBody.selectedItem.toString(),
            spinnerCooler.selectedItem.toString(),
            spinnerTypeEngine.selectedItem.toString(),
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