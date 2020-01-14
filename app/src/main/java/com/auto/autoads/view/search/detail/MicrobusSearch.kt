package com.auto.autoads.view.search.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.ad.AdComplexSearchManager
import com.auto.autoads.model.db.DatabaseEntry.getCategories
import com.auto.autoads.model.db.DatabaseEntry.getConsist
import com.auto.autoads.model.db.DatabaseEntry.getFuel
import com.auto.autoads.model.db.DatabaseEntry.getLocation
import com.auto.autoads.model.db.DatabaseEntry.getMicrobusMarks
import com.auto.autoads.model.db.DatabaseEntry.getMicrobusModels
import com.auto.autoads.model.db.DatabaseEntry.getTransmissions
import com.auto.autoads.model.db.DatabaseEntry.getTypeMicrobus
import com.auto.autoads.model.db.DatabaseEntry.getUnit
import com.auto.autoads.model.db.DatabaseEntry.getWheel
import com.auto.autoads.model.db.DatabaseEntry.getcountryRegistration
import com.auto.autoads.model.utils.makeDef
import com.auto.autoads.view.search.SearchAdResultCallback
import com.auto.autoads.view.search.SearchResultActivity
import kotlinx.android.synthetic.main.activity_search_microbus.*

class MicrobusSearch : AppCompatActivity(), SearchAdResultCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_microbus)

        spinnerCategory.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getCategories()
            )
        spinnerDriveUnit.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getUnit()
            )
        spinnerMarka.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getMicrobusMarks()
            )
        spinnerMarka.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinnerModel.adapter =
                    ArrayAdapter<String>(
                        this@MicrobusSearch,
                        android.R.layout.simple_spinner_dropdown_item,
                        getMicrobusModels(getMicrobusMarks()[p2])
                    )
            }
        }

        spinnerRegistrationCountry.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getcountryRegistration()
            )

        spinnerTypeTransmission.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getTransmissions()
            )
        spinnerTypeBody.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getTypeMicrobus()
            )
        spinnerWheel.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getWheel()
            )

        spinnerConsist.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getConsist()
            )
        spinnerLocation.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getLocation()
            )
        spinnerTypeFuel.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getFuel()
            )
    }

    fun cancel(view: View) {
        finish()
    }

    fun search(view: View) {
        AdComplexSearchManager.complexSearch(
            this,
            "Микроавтобусы",
            spinnerCategory.selectedItem.toString(),
            spinnerMarka.selectedItem.toString(),
            spinnerModel.selectedItem.toString(),
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
            spinnerTypeBody.selectedItem.toString(),
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