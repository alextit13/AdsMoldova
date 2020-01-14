package com.auto.autoads.view.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity: AppCompatActivity() {

    companion object {
        var urlImage: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        Picasso.get().load(urlImage).into(ssiv)
    }

    fun close(view: View) {
        finish()
    }
}