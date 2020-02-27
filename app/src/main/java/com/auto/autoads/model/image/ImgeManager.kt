package com.auto.autoads.model.image

import android.app.Activity
import android.widget.ImageView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class ImgeManager {

    companion object {
        const val top = "top"
        const val bottom = "bottom "
        const val list = "list "
        var bannerInListImageUrl: MutableList<String> = mutableListOf()
        @JvmStatic
        fun newInstance() = ImgeManager()
    }


    val imageListTopBanner: MutableList<String> = mutableListOf()
    val imageListBottomBanner: MutableList<String> = mutableListOf()
    private var imagesTopWasLoaded = false
    private var imagesBottomWasLoaded = false

    fun getImageTopBanner(
        activity: Activity,
        iv: ImageView
    ) {
        FirebaseDatabase.getInstance().getReference(top)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (imagesTopWasLoaded) return

                    imagesTopWasLoaded = true
                    imageListTopBanner.clear()
                    for (child in snapshot.children) {
                        val url = child.value.toString()
                        imageListTopBanner.add(url)
                    }
                    startFlipperForTopBanner(activity, iv)
                }
            })
    }

    private fun startFlipperForTopBanner(activity: Activity, iv: ImageView) {
        Thread(Runnable {
            for (url in imageListTopBanner) {
                activity.runOnUiThread {
                    println("url_test_img = $url")
                    Picasso.get().load(url).resize(600, 200).into(iv)
                }
                Thread.sleep(7000)
            }
            println("url_test_img = returned")
            startFlipperForTopBanner(activity, iv)
        }).start()
    }

    fun getImageBottomBanner(iv: ImageView, activity: Activity) {
        FirebaseDatabase.getInstance().getReference(bottom)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (imagesBottomWasLoaded) return

                    imagesBottomWasLoaded = true
                    imageListBottomBanner.clear()
                    for (child in snapshot.children) {
                        val url = child.value.toString()
                        imageListBottomBanner.add(url)
                    }
                    startFlipperForBottomBanner(activity, iv)
                }
            })
    }

    private fun startFlipperForBottomBanner(activity: Activity, iv: ImageView) {
        Thread(Runnable {
            for (url in imageListBottomBanner) {
                activity.runOnUiThread {
                    Picasso.get().load(url).resize(600, 200).into(iv)
                }
                Thread.sleep(7000)
            }
            startFlipperForBottomBanner(activity, iv)
        }).start()
    }

    fun getImageLinkForListBaner() {
        FirebaseDatabase.getInstance().getReference(list)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {}

                override fun onDataChange(p0: DataSnapshot) {
                    try {
                        bannerInListImageUrl =
                            (p0.value as HashMap<String, String>).values.toMutableList()
                    } catch (e: Exception) {
                        println("error: $e")
                    }
                }
            })
    }
}