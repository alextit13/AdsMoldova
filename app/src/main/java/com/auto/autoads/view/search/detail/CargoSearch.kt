package com.auto.autoads.view.search.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.ad.AdComplexSearchManager
import com.auto.autoads.model.db.DatabaseEntry.getCargoMarks
import com.auto.autoads.model.db.DatabaseEntry.getCategories
import com.auto.autoads.model.db.DatabaseEntry.getConsist
import com.auto.autoads.model.db.DatabaseEntry.getFuel
import com.auto.autoads.model.db.DatabaseEntry.getLocation
import com.auto.autoads.model.db.DatabaseEntry.getTransmissions
import com.auto.autoads.model.db.DatabaseEntry.getTypeCargo
import com.auto.autoads.model.db.DatabaseEntry.getUnitCargo
import com.auto.autoads.model.db.DatabaseEntry.getWheel
import com.auto.autoads.model.db.DatabaseEntry.getcountryRegistration
import com.auto.autoads.model.utils.makeDef
import com.auto.autoads.view.search.SearchAdResultCallback
import com.auto.autoads.view.search.SearchResultActivity
import kotlinx.android.synthetic.main.activty_search_cargo.*

class CargoSearch : AppCompatActivity(), SearchAdResultCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_search_cargo)

        spinnerCategory.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getCategories()
            )
        spinnerMarka.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getCargoMarks()
            )
        spinnerRegistrationCountry.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getcountryRegistration()
            )
        spinnerTypeFuel.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getFuel()
            )
        spinnerTypeTransmission.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getTransmissions()
            )
        spinnerTypeCargo.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getTypeCargo()
            )
        spinnerDriveUnit.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getUnitCargo()
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
        spinnerWheel.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getWheel()
            )
    }

    fun search(view: View) {
        AdComplexSearchManager.complexSearch(
            this,
            "Грузовые авто",
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
            spinnerDriveUnit.selectedItem.toString(),
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
            spinnerTypeCargo.selectedItem.toString(),
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

    fun cancel(view: View) {
        finish()
    }
}