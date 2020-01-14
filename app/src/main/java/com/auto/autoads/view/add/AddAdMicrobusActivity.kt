package com.auto.autoads.view.add

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.SpManager
import com.auto.autoads.model.db.DatabaseEntry
import com.auto.autoads.model.db.DatabaseEntry.getCategories
import com.auto.autoads.model.db.DatabaseEntry.getConsist
import com.auto.autoads.model.db.DatabaseEntry.getLocation
import com.auto.autoads.model.db.DatabaseEntry.getMicrobusMarks
import com.auto.autoads.model.db.DatabaseEntry.getMicrobusModels
import com.auto.autoads.model.db.DatabaseEntry.getTransmissions
import com.auto.autoads.model.db.DatabaseEntry.getTypeMicrobus
import com.auto.autoads.model.db.DatabaseEntry.getWheel
import com.auto.autoads.model.db.DatabaseEntry.getcountryRegistration
import com.auto.autoads.model.utils.Ad
import com.auto.autoads.model.utils.DataHandler
import kotlinx.android.synthetic.main.activity_add_ad_microbus.*
import kotlinx.android.synthetic.main.activity_add_ad_microbus.etColor
import kotlinx.android.synthetic.main.activity_add_ad_microbus.etDescription
import kotlinx.android.synthetic.main.activity_add_ad_microbus.etDistance
import kotlinx.android.synthetic.main.activity_add_ad_microbus.etEngineValue
import kotlinx.android.synthetic.main.activity_add_ad_microbus.etPhone_1
import kotlinx.android.synthetic.main.activity_add_ad_microbus.etPhone_2
import kotlinx.android.synthetic.main.activity_add_ad_microbus.etPhone_3
import kotlinx.android.synthetic.main.activity_add_ad_microbus.etPhone_4
import kotlinx.android.synthetic.main.activity_add_ad_microbus.etPrice
import kotlinx.android.synthetic.main.activity_add_ad_microbus.etYear
import kotlinx.android.synthetic.main.activity_add_ad_microbus.spinnerCategory
import kotlinx.android.synthetic.main.activity_add_ad_microbus.spinnerConsist
import kotlinx.android.synthetic.main.activity_add_ad_microbus.spinnerLocation
import kotlinx.android.synthetic.main.activity_add_ad_microbus.spinnerMarka
import kotlinx.android.synthetic.main.activity_add_ad_microbus.spinnerModel
import kotlinx.android.synthetic.main.activity_add_ad_microbus.spinnerRegistrationCountry
import kotlinx.android.synthetic.main.activity_add_ad_microbus.spinnerTypeBody
import kotlinx.android.synthetic.main.activity_add_ad_microbus.spinnerTypeFuel
import kotlinx.android.synthetic.main.activity_add_ad_microbus.spinnerTypeTransmission
import kotlinx.android.synthetic.main.activity_add_ad_microbus.spinnerWheel
import java.lang.Exception
import java.util.*

class AddAdMicrobusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ad_microbus)

        initSpinners()
    }

    private fun initSpinners() {
        spinnerCategory.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getCategories()
            )
        spinnerMarka.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getMicrobusMarks()
            )
        spinnerTypeFuel.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getFuel()
            )
        spinnerDriveUnit.adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getUnit()
            )
        spinnerMarka.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinnerModel.adapter =
                    ArrayAdapter<String>(
                        this@AddAdMicrobusActivity,
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
    }

    fun cancel(view: View) {
        finish()
    }

    fun next(view: View) {
        try {
            DataHandler.adForSend = Ad(
                Date().time,
                "Микроавтобусы",
                false,
                spinnerCategory.selectedItem.toString(),
                spinnerMarka.selectedItem.toString(),
                spinnerModel.selectedItem.toString(),
                etYear.text.toString().toInt(),
                spinnerTypeFuel.selectedItem.toString(),
                etEngineValue.text.toString().toInt(),
                if (etPrice.text.toString() != "")
                    etPrice.text.toString().toInt() else 0,
                spinnerRegistrationCountry.selectedItem.toString(),
                spinnerTypeTransmission.selectedItem.toString(),
                etDistance.text.toString().toInt(),
                spinnerTypeBody.selectedItem.toString(),
                etColor.text.toString(),
                spinnerDriveUnit.selectedItem.toString(),
                spinnerWheel.selectedItem.toString(),
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