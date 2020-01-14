package com.auto.autoads.model

import android.app.Application
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class ApplicationProvider : Application() {

    companion object {
        lateinit var instance: ApplicationProvider
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        iteratorAdd()
    }

    private fun iteratorAdd() {
        val date = Date().time
        FirebaseDatabase.getInstance().getReference("ITERATOR").child(date.toString())
            .setValue(date)
    }
}