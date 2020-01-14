package com.auto.autoads.view.add

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.view.search.detail.*

class AddAdActivity : AppCompatActivity() {

    private var target = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_main)

        target = intent.getIntExtra("target", 1)
    }

    fun car(view: View) {
        startActivity(
            Intent(
                this,
                if (target == 1) AddAdCarActivity::class.java else SearchCar::class.java
            )
        )
    }

    fun track(view: View) {
        startActivity(
            Intent(
                this,
                if (target == 1) AddAdCargoActivity::class.java else CargoSearch::class.java
            )
        )
    }

    fun bus(view: View) {
        startActivity(
            Intent(
                this,
                if (target == 1) AddAdBusActivity::class.java else BusSearch::class.java
            )
        )
    }

    fun microbus(view: View) {
        startActivity(
            Intent(
                this,
                if (target == 1) AddAdMicrobusActivity::class.java else MicrobusSearch::class.java
            )
        )
    }

    fun moto(view: View) {
        startActivity(
            Intent(
                this,
                if (target == 1) AddAdMotoActivity::class.java else SearchMoto::class.java
            )
        )
    }

    fun parts(view: View) {
        startActivity(
            Intent(
                this,
                if (target == 1) AddAdPartsActivity::class.java else SearchParts::class.java
            )
        )
    }

    fun tires(view: View) {
        startActivity(
            Intent(
                this,
                if (target == 1) AddAdTiresActivity::class.java else TiresSearch::class.java
            )
        )
    }

    fun service(view: View) {
        startActivity(
            Intent(
                this,
                if (target == 1) AddAdServiceActivity::class.java else SearchServices::class.java
            )
        )
    }
}