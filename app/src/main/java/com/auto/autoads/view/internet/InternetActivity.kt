package com.auto.autoads.view.internet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import kotlinx.android.synthetic.main.activity_internet.*

class InternetActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internet)

        btnClose.setOnClickListener { finish() }
    }
}