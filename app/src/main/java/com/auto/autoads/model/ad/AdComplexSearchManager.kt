package com.auto.autoads.model.ad

import android.widget.Toast
import com.auto.autoads.model.ApplicationProvider
import com.auto.autoads.model.ad.AdManager.ADS
import com.auto.autoads.model.ad.AdManager.listSimpleSearchResult
import com.auto.autoads.model.utils.Ad
import com.auto.autoads.view.search.SearchAdResultCallback
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object AdComplexSearchManager {

    fun complexSearch(
        callback: SearchAdResultCallback,
        type: String,
        category: String,
        marka: String,
        model: String,
        yearFrom: Int,
        yearTo: Int,
        typeFuel: String,
        engineValueFrom: Int,
        engineValueTo: Int,
        priceFrom: Int,
        priceTo: Int,
        registrationCountry: String,
        transmission: String,
        distanceFrom: Int,
        distanceTo: Int,
        body: String,
        color: String,
        unit: String,
        wheel: String,
        doorsFrom: Int,
        doorsTo: Int,
        consist: String,
        location: String,
        isNotFixedPrice: Boolean,
        diameterFrom: Int,
        diameterTo: Int,
        widthFrom: Int,
        widthTo: Int,
        heightFrom: Int,
        heightTo: Int,
        placesFrom: Int,
        placesTo: Int,
        typeCargo: String,
        typeMoto: String,
        typeCoolingSystem: String,
        typeEngine: String,
        season: String,
        forCars: String
    ) {
        FirebaseDatabase.getInstance().getReference(ADS)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Toast.makeText(ApplicationProvider.instance, "Ошибка поиска", Toast.LENGTH_LONG)
                        .show()
                }

                override fun onDataChange(data: DataSnapshot) {
                    listSimpleSearchResult.clear()
                    for (child in data.children) {
                        val result = mutableListOf<Int>()
                        val item = child.getValue(Ad::class.java) ?: Ad()

                        if (item.isApprove) result.add(1) else result.add(2)
                        if (type == "" || item.type == type) result.add(1) else result.add(2)
                        if (category == "" || item.category == category) result.add(1) else result.add(
                            2
                        )
                        if (marka == "" || item.marka == marka) result.add(1) else result.add(2)
                        if (model == "" || item.model == model) result.add(1) else result.add(2)
                        if (yearFrom == 0 || item.year == 0 || item.year!! >= yearFrom) result.add(1) else result.add(
                            2
                        )
                        if (yearTo == 0 || item.year == 0 || item.year!! <= yearTo) result.add(1) else result.add(
                            2
                        )
                        if (typeFuel == "" || item.typeFuel == typeFuel) result.add(1) else result.add(
                            2
                        )
                        if (engineValueFrom == 0 || item.engineValue == 0 || item.engineValue!! >= engineValueFrom) result.add(
                            1
                        ) else result.add(2)
                        if (engineValueTo == 0 || item.engineValue == 0 || item.engineValue!! <= engineValueTo) result.add(
                            1
                        ) else result.add(2)
                        if (priceFrom == 0 || item.price == 0 || item.price!! >= priceFrom) result.add(
                            1
                        ) else result.add(2)
                        if (priceTo == 0 || item.price == 0 || item.price!! <= priceTo) result.add(1) else result.add(
                            2
                        )
                        if (registrationCountry == "" || item.registrationCountry == registrationCountry) result.add(
                            1
                        ) else result.add(2)
                        if (model == "" || item.model == model) result.add(1) else result.add(2)
                        if (transmission == "" || item.transmission == transmission) result.add(1) else result.add(
                            2
                        )
                        if (distanceFrom == 0 || item.distance == 0 || item.distance!! >= distanceFrom) result.add(
                            1
                        ) else result.add(2)
                        if (distanceTo == 0 || item.distance == 0 || item.distance!! <= distanceTo) result.add(
                            1
                        ) else result.add(2)
                        if (body == "" || item.body == body) result.add(1) else result.add(2)
                        if (color == "" || item.color == color) result.add(1) else result.add(2)
                        if (unit == "" || item.unit == unit) result.add(1) else result.add(2)
                        if (wheel == "" || item.wheel == wheel) result.add(1) else result.add(2)
                        if (doorsFrom == 0 || item.doors == 0 || item.doors!! == doorsFrom) result.add(
                            1
                        ) else result.add(2)
                        if (doorsTo == 0 || item.doors == 0 || item.doors!! <= doorsTo) result.add(1) else result.add(
                            2
                        )
                        if (consist == "" || item.consist == consist) result.add(1) else result.add(
                            2
                        )
                        if (location == "" || item.location == location) result.add(1) else result.add(
                            2
                        )
                        //if (isNotFixedPrice && item.isNotFixedPrice == true) result.add(1) else result.add(2)
                        if (diameterFrom == 0 || item.diameter == 0 || item.diameter!! >= diameterFrom) result.add(
                            1
                        ) else result.add(2)
                        if (diameterTo == 0 || item.diameter == 0 || item.diameter!! <= diameterTo) result.add(
                            1
                        ) else result.add(2)
                        if (widthFrom == 0 || item.width == 0 || item.width!! >= widthFrom) result.add(
                            1
                        ) else result.add(2)
                        if (widthTo == 0 || item.width == 0 || item.width!! <= widthTo) result.add(1) else result.add(
                            2
                        )
                        if (heightFrom == 0 || item.height == 0 || item.height!! >= heightFrom) result.add(
                            1
                        ) else result.add(2)
                        if (heightTo == 0 || item.height == 0 || item.height!! <= heightTo) result.add(
                            1
                        ) else result.add(2)
                        if (placesFrom == 0 || item.places == 0 || item.places >= placesFrom) result.add(
                            1
                        ) else result.add(2)
                        if (placesTo == 0 || item.places == 0 || item.places <= placesTo) result.add(
                            1
                        ) else result.add(2)

                        if (typeCargo == "" || item.typeCargo == typeCargo) result.add(1) else result.add(
                            2
                        )

                        if (typeMoto == "" || item.typeMoto == typeMoto) result.add(1) else result.add(
                            2
                        )

                        if (typeCoolingSystem == "" || item.typeCoolingSystem == typeCoolingSystem) result.add(1) else result.add(
                            2
                        )

                        if (typeEngine == "" || item.typeEngine == typeEngine) result.add(1) else result.add(
                            2
                        )
                        if (season == "" || item.season == season) result.add(1) else result.add(
                            2
                        )
                        if (forCars == "" || item.forCars == forCars) result.add(1) else result.add(
                            2
                        )

                        if (!result.contains(2)) listSimpleSearchResult.add(item)
                    }

                    if (listSimpleSearchResult.isEmpty()) callback.onError() else callback.onSearchResult()
                }
            })
    }
}