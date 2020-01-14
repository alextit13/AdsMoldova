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
import com.auto.autoads.model.utils.Ad
import com.auto.autoads.model.utils.DataHandler
import kotlinx.android.synthetic.main.activity_add_ad_car.*
import java.util.*

class AddAdCarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ad_car)

        initSpinners()
    }

    private fun initSpinners() {
        spinnerCategory.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getCategories()
            )

        spinnerMarka.apply {
            adapter = ArrayAdapter<String>(
                this@AddAdCarActivity,
                android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getCarMarkas()
            )
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    spinnerModel.adapter =
                        ArrayAdapter<String>(
                            this@AddAdCarActivity, android.R.layout.simple_spinner_dropdown_item,
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

    fun initListeners() {
        btnCancel.setOnClickListener { finish() }
    }

    fun cancel(view: View) {
        finish()
    }

    fun next(view: View) {
        try {
            DataHandler.adForSend = Ad(
                Date().time,
                "Легковые авто",
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
                spinnerNumDoors.selectedItem.toString().toInt(),
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
                0
            )
            startActivity(Intent(this, ImageChooserActivity::class.java))
        } catch (e: Exception) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
        }
    }
}