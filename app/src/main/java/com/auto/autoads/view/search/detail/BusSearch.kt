package com.auto.autoads.view.search.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.ad.AdComplexSearchManager
import com.auto.autoads.model.db.DatabaseEntry.getBusMarks
import com.auto.autoads.model.db.DatabaseEntry.getCategories
import com.auto.autoads.model.db.DatabaseEntry.getConsist
import com.auto.autoads.model.db.DatabaseEntry.getFuel
import com.auto.autoads.model.db.DatabaseEntry.getLocation
import com.auto.autoads.model.db.DatabaseEntry.getTransmissions
import com.auto.autoads.model.db.DatabaseEntry.getWheel
import com.auto.autoads.model.db.DatabaseEntry.getcountryRegistration
import com.auto.autoads.model.utils.makeDef
import com.auto.autoads.view.search.SearchAdResultCallback
import com.auto.autoads.view.search.SearchResultActivity
import kotlinx.android.synthetic.main.activity_search_bus.*

class BusSearch : AppCompatActivity(), SearchAdResultCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_bus)

        spinnerCategory.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item
                , getCategories()
            )
        spinnerMarka.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getBusMarks()
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

        spinnerLocation.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getLocation()
            )

        spinnerTypeTransmission.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getTransmissions()
            )

        spinnerWheel.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getWheel()
            )

        spinnerConsist.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getConsist()
            )

    }

    fun cancel(view: View) {
        finish()
    }

    fun search(view: View) {
        AdComplexSearchManager.complexSearch(
            this,
            "Автобусы",
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
            spinnerTypeTransmission.selectedItem.toString(),
            0,
            Int.MAX_VALUE,
            "",
            "",
            "",
            spinnerWheel.selectedItem.toString(),
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