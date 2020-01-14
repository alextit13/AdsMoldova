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
import com.auto.autoads.model.db.DatabaseEntry.getForCarTypes
import com.auto.autoads.model.db.DatabaseEntry.getTypeCategory
import com.auto.autoads.model.utils.Ad
import com.auto.autoads.model.utils.DataHandler
import kotlinx.android.synthetic.main.activity_add_ad_part.*
import kotlinx.android.synthetic.main.activity_add_ad_part.etDescription
import kotlinx.android.synthetic.main.activity_add_ad_part.etPhone_1
import kotlinx.android.synthetic.main.activity_add_ad_part.etPhone_2
import kotlinx.android.synthetic.main.activity_add_ad_part.etPhone_3
import kotlinx.android.synthetic.main.activity_add_ad_part.etPhone_4
import kotlinx.android.synthetic.main.activity_add_ad_part.etPrice
import kotlinx.android.synthetic.main.activity_add_ad_part.price_not_fix
import kotlinx.android.synthetic.main.activity_add_ad_part.spinnerCategory
import kotlinx.android.synthetic.main.activity_add_ad_part.spinnerLocation
import java.lang.Exception
import java.util.*

class AddAdPartsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ad_part)
        initSpinners()
    }

    private fun initSpinners() {
        spinnerCategory.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getTypeCategory()
            )
        spinnerType.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getForCarTypes()
            )
        spinnerLocation.adapter =
            ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                DatabaseEntry.getLocation()
            )
    }

    fun cancel(view: View) {
        finish()
    }

    fun next(view: View) {
        try {
            DataHandler.adForSend = Ad(
                Date().time,
                "Запчасти",
                false,
                spinnerCategory.selectedItem.toString(),
                spinnerType.selectedItem.toString(),
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