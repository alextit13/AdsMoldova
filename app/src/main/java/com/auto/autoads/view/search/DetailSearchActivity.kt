package com.auto.autoads.view.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.db.DatabaseEntry
import kotlinx.android.synthetic.main.activity_detail_search.*

class DetailSearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_search)
    }

    private fun showAll() {
        // tvModel.visibility = View.VISIBLE
        // spinnerModel.visibility = View.VISIBLE
        tvTypeFuel.visibility = View.VISIBLE
        spinnerTypeFuel.visibility = View.VISIBLE
        tvTransmission.visibility = View.VISIBLE
        spinnerTypeTransmission.visibility = View.VISIBLE
        typeBody.visibility = View.VISIBLE
        spinnerTypeBody.visibility = View.VISIBLE
    }

    fun searchDetail(view: View) {
        type = spinnerType.selectedItem?.toString()
        kategory = ""
        marka = ""
        model = ""
        yearFrom = etYearFrom.text.toString()
        yearTo = etYearTo.text.toString()
        fuel = spinnerTypeFuel.selectedItem?.toString()
        valueFrom = etEngineValueFrom.text.toString()
        valueTo = etEngineValueTo.text.toString()
        priceFrom = etPriceFrom.text.toString()
        priceTo = etPriceTo.text.toString()
        transmission = spinnerTypeTransmission.selectedItem?.toString()
        distanceFrom = etDistanceFrom.text.toString()
        distanceTo = etDistanceTo.text.toString()
        body = spinnerTypeBody.selectedItem?.toString()

        SearchResultActivity.s = "category"
        startActivity(Intent(this, SearchResultActivity::class.java))
    }

    companion object {
        var type: String? = null
        var kategory: String? = null
        var marka: String? = null
        var model: String? = null
        var yearFrom: String? = null
        var yearTo: String? = null
        var fuel: String? = null
        var valueFrom: String? = null
        var valueTo: String? = null
        var priceFrom: String? = null
        var priceTo: String? = null
        var transmission: String? = null
        var distanceFrom: String? = null
        var distanceTo: String? = null
        var body: String? = null
    }

    fun cancelSearch(view: View) {
        finish()
    }
}