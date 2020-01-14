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
import com.auto.autoads.model.db.DatabaseEntry
import com.auto.autoads.model.utils.makeDef
import com.auto.autoads.view.search.SearchAdResultCallback
import com.auto.autoads.view.search.SearchResultActivity
import kotlinx.android.synthetic.main.activity_add_ad_car.*
import kotlinx.android.synthetic.main.activity_add_ad_car.spinnerCategory
import kotlinx.android.synthetic.main.activity_add_ad_car.spinnerConsist
import kotlinx.android.synthetic.main.activity_add_ad_car.spinnerDriveUnit
import kotlinx.android.synthetic.main.activity_add_ad_car.spinnerLocation
import kotlinx.android.synthetic.main.activity_add_ad_car.spinnerMarka
import kotlinx.android.synthetic.main.activity_add_ad_car.spinnerRegistrationCountry
import kotlinx.android.synthetic.main.activity_add_ad_car.spinnerTypeFuel
import kotlinx.android.synthetic.main.activity_add_ad_car.spinnerTypeTransmission
import kotlinx.android.synthetic.main.activity_add_ad_car.spinnerWheel
import kotlinx.android.synthetic.main.activty_search_cargo.*

class SearchCar : AppCompatActivity(), SearchAdResultCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_car)

        spinnerCategory.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getCategories()
            )

        spinnerMarka.apply {
            adapter = ArrayAdapter<String>(
                this@SearchCar,
                android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getCarMarkas()
            )
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    spinnerModel.adapter =
                        ArrayAdapter<String>(
                            this@SearchCar, android.R.layout.simple_spinner_dropdown_item,
                            DatabaseEntry.getModels(DatabaseEntry.getCarMarkas().get(p2))
                        )
                }
            }
        }
        spinnerTypeFuel.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getFuel()
            )
        spinnerRegistrationCountry.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getcountryRegistration()
            )

        spinnerTypeTransmission.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getTransmissions()
            )

        spinnerTypeBody.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getBody()
            )

        spinnerDriveUnit.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getUnit()
            )

        spinnerWheel.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getWheel()
            )

        spinnerNumDoors.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getDoors()
            )

        spinnerConsist.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getConsist()
            )

        /*spinnerSafety.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getSafety()
            )

        spinnerComplectation.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getComplectation()
            )*/

        spinnerLocation.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getLocation()
            )
    }

    fun cancel(view: View) {
        finish()
    }

    fun search(view: View) {
        AdComplexSearchManager.complexSearch(
            this,
            "Легковые авто",
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
            if (spinnerNumDoors.selectedItem.toString() == "") 0 else spinnerNumDoors.selectedItem.toString().toInt(),
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