package com.auto.autoads.model.image

import android.widget.ImageView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

object ImgeManager {

    const val top = "top"
    const val bottom = "bottom "
    const val list = "list "

    var bannerInListImageUrl = ""

    fun getImageTopBanner(iv: ImageView) {
        FirebaseDatabase.getInstance().getReference(top)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    Picasso.get().load(p0.value?.toString()).into(iv)
                }
            })
    }

    fun getImageBottomBanner(iv: ImageView) {
        FirebaseDatabase.getInstance().getReference(bottom)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    Picasso.get().load(p0.value?.toString()).into(iv)
                }
            })
    }

    fun getImageLinkForListBaner(){
        FirebaseDatabase.getInstance().getReference(list).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                bannerInListImageUrl = p0.value?.toString() ?: ""
            }
        })
    }
}