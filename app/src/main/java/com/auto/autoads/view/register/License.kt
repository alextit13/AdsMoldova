package com.auto.autoads.view.register

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import kotlinx.android.synthetic.main.activity_license.*

class License : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_license)
        tvLicense.movementMethod = ScrollingMovementMethod()
    }

    fun confirm(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    fun cancel(view: View) {
        finish()
    }
}