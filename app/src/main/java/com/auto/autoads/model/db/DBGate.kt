package com.auto.autoads.model.db

import android.content.ContentValues
import com.auto.autoads.model.ApplicationProvider
import com.auto.autoads.model.utils.Ad
import com.google.gson.Gson

class DBGate {

    /*fun insert(ad: Ad) {
        val adJson = Gson().toJson(ad)
        val cv = ContentValues()
        val dbHelper = DbHelper.newInstance(ApplicationProvider.instance)
        val db = dbHelper.writableDatabase

        cv.put("dataJson", adJson)

        db.insert("favorit_ads", null, cv)
        dbHelper.close()
    }

    fun getAll(): MutableList<Ad> {
        val list = mutableListOf<Ad>()
        val dbHelper = DbHelper.newInstance(ApplicationProvider.instance)
        val db = dbHelper.writableDatabase

        val c = db.query(
            "favorit_ads", null, null, null,
            null, null, null
        )

        if (c.moveToFirst()) {
            val dataColumnIndex = c.getColumnIndex("dataJson")

            do {
                val data = c.getString(dataColumnIndex)
                val ad = Gson().fromJson<Ad>(data, Ad::class.java)
                list.add(ad)
            } while (c.moveToNext())
        }

        c.close()

        return list
    }

    fun delete(data: String): Boolean {
        return DbHelper.newInstance(ApplicationProvider.instance).writableDatabase
            .delete("favorit_ads", "dataJson=?", arrayOf(data)) > 0
    }*/

    companion object {
        @JvmStatic
        fun newInstance() = DBGate()
    }
}