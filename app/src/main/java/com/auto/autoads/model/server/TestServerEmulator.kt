package com.auto.autoads.model.server

import com.auto.autoads.model.utils.Ad

object TestServerEmulator {
    fun getListAdMock(callback: IListAdListener, num: Int) {
        val list = mutableListOf<Ad>()
        /*for (i in 0..num) {
            listImages.add(Ad(
                category,
                marka,
                model,
                year,
                typeFuel,
                engineValue,
                price,
                registrationCountry,
                transmission,
                distance,
                body,
                color,
                unit,
                wheel,
                doors,
                consist,
                safety,
                complectation,
                location,
                subscription
            ).apply {
                id = i.toLong()
                // title = "Title test car"
                description = "Description of test mock car"
                type = "Car"
                user = "user id"
                linkImages = getListImages()
            })*/
    }
    //callback.onListAdsResult(listImages)
}