package com.auto.autoads.view.add

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.SpManager
import com.auto.autoads.model.db.DatabaseEntry.getLocation
import com.auto.autoads.model.db.DatabaseEntry.getTypeCategory
import com.auto.autoads.model.utils.Ad
import com.auto.autoads.model.utils.DataHandler
import kotlinx.android.synthetic.main.activity_add_ad_tires.*
import java.util.*

class AddAdSomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ad_some)
        initSpinners()
    }

    private fun initSpinners() {
        spinnerCategory.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getTypeCategory()
            )
        /*spinnerSeasons.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getSiasons()
            )
        spinnerConsist.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getTiresConsist()
            )
        spinnerForCars.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getForCarTypes()
            )*/
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
                "Разное",
                false,
                spinnerCategory.selectedItem.toString(),
                "",
                "",
                0,
                "",
                0,
                if (etPrice.text.toString() != "")
                    etPrice.text.toString().toInt() else 0,
                "",
                "",
                0,
                "",
                "",
                "",
                "",
                0,
                "",
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