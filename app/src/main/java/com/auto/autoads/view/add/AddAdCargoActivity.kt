package com.auto.autoads.view.add

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.SpManager
import com.auto.autoads.model.db.DatabaseEntry
import com.auto.autoads.model.db.DatabaseEntry.getCargoMarks
import com.auto.autoads.model.db.DatabaseEntry.getCategories
import com.auto.autoads.model.db.DatabaseEntry.getConsist
import com.auto.autoads.model.db.DatabaseEntry.getLocation
import com.auto.autoads.model.db.DatabaseEntry.getTransmissions
import com.auto.autoads.model.db.DatabaseEntry.getTypeCargo
import com.auto.autoads.model.db.DatabaseEntry.getUnitCargo
import com.auto.autoads.model.db.DatabaseEntry.getcountryRegistration
import com.auto.autoads.model.utils.Ad
import com.auto.autoads.model.utils.DataHandler
import kotlinx.android.synthetic.main.activity_add_ad_cargo.*
import kotlinx.android.synthetic.main.activity_add_ad_cargo.etColor
import kotlinx.android.synthetic.main.activity_add_ad_cargo.etDescription
import kotlinx.android.synthetic.main.activity_add_ad_cargo.etDistance
import kotlinx.android.synthetic.main.activity_add_ad_cargo.etEngineValue
import kotlinx.android.synthetic.main.activity_add_ad_cargo.etPhone_1
import kotlinx.android.synthetic.main.activity_add_ad_cargo.etPhone_2
import kotlinx.android.synthetic.main.activity_add_ad_cargo.etPhone_3
import kotlinx.android.synthetic.main.activity_add_ad_cargo.etPhone_4
import kotlinx.android.synthetic.main.activity_add_ad_cargo.etPrice
import kotlinx.android.synthetic.main.activity_add_ad_cargo.etYear
import kotlinx.android.synthetic.main.activity_add_ad_cargo.price_not_fix
import kotlinx.android.synthetic.main.activity_add_ad_cargo.spinnerCategory
import kotlinx.android.synthetic.main.activity_add_ad_cargo.spinnerConsist
import kotlinx.android.synthetic.main.activity_add_ad_cargo.spinnerDriveUnit
import kotlinx.android.synthetic.main.activity_add_ad_cargo.spinnerLocation
import kotlinx.android.synthetic.main.activity_add_ad_cargo.spinnerMarka
import kotlinx.android.synthetic.main.activity_add_ad_cargo.spinnerRegistrationCountry
import kotlinx.android.synthetic.main.activity_add_ad_cargo.spinnerTypeFuel
import kotlinx.android.synthetic.main.activity_add_ad_cargo.spinnerTypeTransmission
import kotlinx.android.synthetic.main.activity_add_ad_moto.*
import java.lang.Exception
import java.util.*

class AddAdCargoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ad_cargo)

        initSpinners()
    }

    private fun initSpinners() {
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
        spinnerTypeFuel.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getFuel()
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
    }

    fun cancel(view: View) {
        finish()
    }

    fun next(view: View) {
        try {
            DataHandler.adForSend = Ad(
                Date().time,
                "Грузовые авто",
                false,
                spinnerCategory.selectedItem.toString(),
                spinnerMarka.selectedItem.toString(),
                "",
                etYear.text.toString().toInt(),
                spinnerTypeFuel.selectedItem.toString(),
                etEngineValue.text.toString().toInt(),
                if (etPrice.text.toString() != "")
                    etPrice.text.toString().toInt() else 0,
                spinnerRegistrationCountry.selectedItem.toString(),
                spinnerTypeTransmission.selectedItem.toString(),
                etDistance.text.toString().toInt(),
                "",
                etColor.text.toString(),
                spinnerDriveUnit.selectedItem.toString(),
                "",
                0,
                spinnerConsist.selectedItem.toString(),
                "",
                "",
                spinnerLocation.selectedItem.toString(),
                etDescription.text.toString(),
                mutableListOf(),
                SpManager.getUser()?.email ?: "",
                etPhone_1.text.toString(),
                etPhone_2.text.toString(),
                etPhone_3.text.toString(),
                etPhone_4.text.toString(),
                price_not_fix.isChecked,
                0,
                0,
                0,
                0,
                spinnerTypeCargo.selectedItem.toString())
            startActivity(Intent(this, ImageChooserActivity::class.java))
        } catch (e: Exception) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
        }
    }
}