package com.auto.autoads.model.utils

import com.auto.autoads.model.ApplicationProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DataHandler {
    var currentUser: User? = null
    var listCheckImages: MutableList<String> = mutableListOf()
    var adForSend: Ad? = null

    enum class TypeAd(val type: String) {
        Auto("Авто"),
        Moto("Мото"),
        Services("Услуги"),
        Tyres("Шины"),
        Parts("Запчасти")
    }

    fun getCarsMap(): Map<String, List<String>> {
        val s = String(ApplicationProvider.instance.assets.open("cars.txt").readBytes())
        val typeMap = object : TypeToken<Map<String, List<String>>>() {}.type
        val map: Map<String, List<String>> = Gson().fromJson(s, typeMap)
        return map
    }

    fun getMicrobusMap(): Map<String, List<String>> {
        val s = String(ApplicationProvider.instance.assets.open("microbus.txt").readBytes())
        val typeMap = object : TypeToken<Map<String, List<String>>>() {}.type
        val map: Map<String, List<String>> = Gson().fromJson(s, typeMap)
        return map
    }
}