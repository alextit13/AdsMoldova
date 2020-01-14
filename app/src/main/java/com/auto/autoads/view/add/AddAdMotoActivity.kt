package com.auto.autoads.view.add

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.SpManager
import com.auto.autoads.model.db.DatabaseEntry.getCategories
import com.auto.autoads.model.db.DatabaseEntry.getConsist
import com.auto.autoads.model.db.DatabaseEntry.getFuel
import com.auto.autoads.model.db.DatabaseEntry.getLocation
import com.auto.autoads.model.db.DatabaseEntry.getMotoMarks
import com.auto.autoads.model.db.DatabaseEntry.getTypeCooling
import com.auto.autoads.model.db.DatabaseEntry.getTypeEngine
import com.auto.autoads.model.db.DatabaseEntry.getTypeMoto
import com.auto.autoads.model.db.DatabaseEntry.getcountryRegistration
import com.auto.autoads.model.utils.Ad
import com.auto.autoads.model.utils.DataHandler
import kotlinx.android.synthetic.main.activity_add_ad_moto.*
import kotlinx.android.synthetic.main.activity_add_ad_moto.etDescription
import kotlinx.android.synthetic.main.activity_add_ad_moto.etDistance
import kotlinx.android.synthetic.main.activity_add_ad_moto.etEngineValue
import kotlinx.android.synthetic.main.activity_add_ad_moto.etPhone_1
import kotlinx.android.synthetic.main.activity_add_ad_moto.etPhone_2
import kotlinx.android.synthetic.main.activity_add_ad_moto.etPhone_3
import kotlinx.android.synthetic.main.activity_add_ad_moto.etPhone_4
import kotlinx.android.synthetic.main.activity_add_ad_moto.etPrice
import kotlinx.android.synthetic.main.activity_add_ad_moto.etYear
import kotlinx.android.synthetic.main.activity_add_ad_moto.price_not_fix
import kotlinx.android.synthetic.main.activity_add_ad_moto.spinnerCategory
import kotlinx.android.synthetic.main.activity_add_ad_moto.spinnerConsist
import kotlinx.android.synthetic.main.activity_add_ad_moto.spinnerLocation
import kotlinx.android.synthetic.main.activity_add_ad_moto.spinnerMarka
import kotlinx.android.synthetic.main.activity_add_ad_moto.spinnerRegistrationCountry
import kotlinx.android.synthetic.main.activity_add_ad_moto.spinnerTypeBody
import kotlinx.android.synthetic.main.activity_add_ad_moto.spinnerTypeFuel
import java.lang.Exception
import java.util.*

class AddAdMotoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ad_moto)

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

    fun next(view: View) {
        try {
            DataHandler.adForSend = Ad(
                Date().time,
                "Мотоциклы и мототехника",
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
                "",
                etDistance.text.toString().toInt(),
                "",
                "",
                "",
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
                0)
            startActivity(Intent(this, ImageChooserActivity::class.java))
        } catch (e: Exception) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
        }
    }
}