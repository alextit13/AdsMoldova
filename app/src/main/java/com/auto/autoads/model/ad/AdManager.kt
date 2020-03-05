package com.auto.autoads.model.ad

import android.net.Uri
import android.widget.Toast
import com.auto.autoads.model.ApplicationProvider
import com.auto.autoads.model.SpManager
import com.auto.autoads.model.utils.Ad
import com.auto.autoads.model.utils.DataHandler
import com.auto.autoads.view.admin.IAdsAdminResult
import com.auto.autoads.view.main.IListFavorits
import com.auto.autoads.view.user.IDownloadAdsListener
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.io.File

object AdManager {

    const val ADS = "ADS"
    private var isImagesWasUploaded = false
    var listAds = mutableListOf<Ad>()
    var listSimpleSearchResult = mutableListOf<Ad>()
    var listAdminAds = mutableListOf<Ad>()
    var listFavorits = mutableListOf<Ad>()

    fun sendAdToServer(ad: Ad, callback: IadManagerCallback) {
        if (isImagesWasUploaded || DataHandler.listCheckImages.isEmpty()) {
            clearData()
            FirebaseDatabase.getInstance().getReference(ADS).child(ad.id.toString()).setValue(ad)
                .addOnSuccessListener {
                    callback.onAdSendSuccess()
                }
                .addOnFailureListener {
                    callback.onAdSendError()
                }
        } else {
            sendImagesToServer(ad, callback)
        }
    }

    fun clearData() {
        DataHandler.listCheckImages.clear()
        isImagesWasUploaded = false
    }

    private fun sendImagesToServer(ad: Ad, callback: IadManagerCallback) {
        var iterator = 0

        fun send(pathToFile: String) {
            val file = Uri.fromFile(File(pathToFile))

            val ref =
                FirebaseStorage.getInstance().getReferenceFromUrl("gs://autoads-c8f9c.appspot.com")
                    .child(ad.id.toString() + pathToFile.substringAfterLast("/"))
            val uploadTask = ref.putFile(file)
            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            }).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    ad.linkImages?.add(downloadUri.toString())
                    iterator++
                    if (DataHandler.listCheckImages.size > iterator) {
                        if (DataHandler.listCheckImages[iterator] != "") {
                            send(DataHandler.listCheckImages[iterator])
                        } else {
                            isImagesWasUploaded = true
                            sendAdToServer(ad, callback)
                        }
                    } else {
                        isImagesWasUploaded = true
                        sendAdToServer(ad, callback)
                    }
                }
            }
        }
        send(DataHandler.listCheckImages.first())
    }

    fun getMyAds(listener: IDownloadAdsListener) {
        if (listAds.isNotEmpty()) listAds.clear()

        Thread(Runnable {
            Thread.sleep(5000)
            if (listAds.isEmpty()) {
                listener.empty()
            }
        }).start()
        FirebaseDatabase.getInstance().getReference(ADS)
            .addChildEventListener(object : ChildEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onChildMoved(p0: DataSnapshot, p1: String?) {

                }

                override fun onChildChanged(p0: DataSnapshot, p1: String?) {

                }

                override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                    try {
                        val ad = p0.getValue(Ad::class.java)
                        if (ad?.user == SpManager.getUser()?.email) {
                            ad?.let { listAds.add(it) }
                            listener.onMyAds()
                        }
                    } catch (e: Exception) {
                        listener.error()
                    }
                }

                override fun onChildRemoved(p0: DataSnapshot) {

                }
            })
    }

    fun getSimpleSearchAds(text: String, listener: ISimpleSearchListener) {
        listSimpleSearchResult.clear()
        FirebaseDatabase.getInstance().getReference(ADS)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    listener.onError()
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        val ad = it.getValue(Ad::class.java) ?: return
                        if (ad.toString().contains(text, true) && ad.isApprove) {
                            listSimpleSearchResult.add(ad)
                        }
                        listener.onSearchSimpleResult()
                    }
                }
            })
    }

    fun getAllAds(callback: IAdsAdminResult) {
        listAdminAds.clear()
        FirebaseDatabase.getInstance().getReference(ADS)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    callback.allAds()
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (listAdminAds.isNotEmpty()) listAdminAds.clear()
                    for (postSnapshot in snapshot.children) {
                        val ad = postSnapshot.getValue(Ad::class.java) ?: Ad()
                        listAdminAds.add(ad)
                    }
                    callback.allAds()
                }
            })
    }

    fun getFavoritAds(callback: IListFavorits) {
        listFavorits.clear()
        FirebaseDatabase.getInstance().getReference("FORWARD")
            .addChildEventListener(object : ChildEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onChildMoved(p0: DataSnapshot, p1: String?) {

                }

                override fun onChildChanged(p0: DataSnapshot, p1: String?) {

                }

                override fun onChildAdded(data: DataSnapshot, p1: String?) {
                    listFavorits.add(data.getValue(Ad::class.java) ?: Ad())
                    callback.onFavoritsResult()
                }

                override fun onChildRemoved(p0: DataSnapshot) {

                }
            })
    }

    fun deleteAd(ad: Ad) {
        FirebaseDatabase.getInstance().getReference(ADS).child(ad.id?.toString().toString())
            .removeValue()
        Toast.makeText(ApplicationProvider.instance, "Объявление будет удалено", Toast.LENGTH_SHORT)
            .show()
    }

    fun complexSearch(searchString: String, listener: ISimpleSearchListener) {
        listSimpleSearchResult.clear()
        val listTags = searchString.split("|").toMutableList()
        var priceFrom = ""
        var priceTo = ""
        var yearFrom = ""
        var yearTo = ""

        var diametr_from = ""
        var diametr_to = ""
        var width_from = ""
        var width_to = ""
        var height_from = ""
        var height_to = ""

        var placesFrom = ""
        var placesTo = ""
        listTags.forEach {
            if (it.contains("price_from")) {
                priceFrom = it.substringAfterLast("=")
            }
            if (it.contains("price_to")) {
                priceTo = it.substringAfterLast("=")
            }
            if (it.contains("year_from")) {
                yearFrom = it.substringAfterLast("=")
            }
            if (it.contains("year_to")) {
                yearTo = it.substringAfterLast("=")
            }

            if (it.contains("diametr_from")) {
                diametr_from = it.substringAfterLast("=")
            }
            if (it.contains("diametr_to")) {
                diametr_to = it.substringAfterLast("=")
            }
            if (it.contains("width_from")) {
                width_from = it.substringAfterLast("=")
            }
            if (it.contains("width_to")) {
                width_to = it.substringAfterLast("=")
            }
            if (it.contains("height_from")) {
                height_from = it.substringAfterLast("=")
            }
            if (it.contains("height_to")) {
                height_to = it.substringAfterLast("=")
            }

            if (it.contains("num_seats_from")) {
                placesFrom = it.substringAfterLast("=")
            }
            if (it.contains("num_seats_to")) {
                placesTo = it.substringAfterLast("=")
            }
        }

        if (priceFrom == "") priceFrom = "0"
        if (priceTo == "") priceTo = Integer.MAX_VALUE.toString()
        if (yearFrom == "") yearFrom = "0"
        if (yearTo == "") yearTo = Integer.MAX_VALUE.toString()

        if (diametr_from == "") diametr_from = "0"
        if (diametr_to == "") diametr_to = Integer.MAX_VALUE.toString()
        if (width_from == "") width_from = "0"
        if (width_to == "") width_to = Integer.MAX_VALUE.toString()
        if (height_from == "") height_from = "0"
        if (height_to == "") height_to = Integer.MAX_VALUE.toString()

        if (placesFrom == "") placesFrom = "0"
        if (placesTo == "") placesTo = Integer.MAX_VALUE.toString()

        val list = mutableListOf<Ad>()

        FirebaseDatabase.getInstance().getReference(ADS)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Toast.makeText(ApplicationProvider.instance, "Ошибка поиска", Toast.LENGTH_LONG)
                        .show()
                }

                override fun onDataChange(data: DataSnapshot) {
                    data.children.forEach {
                        list.add(it.getValue(Ad::class.java) ?: Ad())
                    }

                    for (i in 0 until listTags.size) {
                        if (listTags[i] == "price_from:= ")
                            listTags[i] = ""
                        if (listTags[i] == "price_to:= ")
                            listTags[i] = ""
                        if (listTags[i] == "year_from:= ")
                            listTags[i] = ""
                        if (listTags[i] == "year_to:= ")
                            listTags[i] = ""

                        if (listTags[i] == "diametr_from:= ")
                            listTags[i] = ""
                        if (listTags[i] == "diametr_to:= ")
                            listTags[i] = ""
                        if (listTags[i] == "width_from:= ")
                            listTags[i] = ""
                        if (listTags[i] == "width_to:= ")
                            listTags[i] = ""
                        if (listTags[i] == "height_from:= ")
                            listTags[i] = ""
                        if (listTags[i] == "height_to:= ")
                            listTags[i] = ""

                        if (listTags[i] == "num_seats_from:= ")
                            listTags[i] = ""
                        if (listTags[i] == "num_seats_to:= ")
                            listTags[i] = ""

                        listTags[i] = listTags[i].replace("price_from:= ", "")
                            .replace("price_to:= ", "")
                            .replace("year_from:= ", "")
                            .replace("year_to:= ", "")

                            .replace("diametr_from:= ", "")
                            .replace("diametr_to:= ", "")
                            .replace("width_from:= ", "")
                            .replace("width_to:= ", "")
                            .replace("height_from:= ", "")
                            .replace("height_to:= ", "")

                            .replace("num_seats_from:= ", "")
                            .replace("num_seats_to:= ", "")
                    }

                    sortAds(
                        list, listTags, priceFrom, priceTo, yearFrom, yearTo,
                        diametr_from, diametr_to, width_from, width_to,
                        height_from, height_to,
                        placesFrom, placesTo,
                        listener
                    )
                }
            })
    }

    private fun sortAds(
        list: MutableList<Ad>,
        listTags: MutableList<String>,
        priceFrom: String,
        priceTo: String,
        yearFrom: String,
        yearTo: String,

        diametr_from: String,
        diametr_to: String,
        width_from: String,
        width_to: String,
        height_from: String,
        height_to: String,

        placesFrom: String,
        placesTo: String,
        listener: ISimpleSearchListener
    ) {
        val listResult = mutableListOf<Ad>()
        list.forEach { ad ->
            var isContain = ""
            listTags.forEach { tag ->
                isContain += if (tag == "" || tag == " " || ad.toString().contains(tag)) {
                    "1"
                } else {
                    "2"
                }
            }
            if (!isContain.contains("2")) {
                listResult.add(ad)
            }
        }

        /*sortByPriceAndYears(
            priceFrom.replace(" ", ""),
            priceTo.replace(" ", ""),
            yearFrom.replace(" ", ""),
            yearTo.replace(" ", ""),
            diametr_from, diametr_to, width_from, width_to,
            height_from, height_to,
            placesFrom, placesTo,
            listResult, listener
        )*/
    }

    /*private fun sortByPriceAndYears(
        priceFrom: String,
        priceTo: String,
        yearFrom: String,
        yearTo: String,
        diametr_from: String,
        diametr_to: String,
        width_from: String,
        width_to: String,
        height_from: String,
        height_to: String,
        placesFrom: String,
        placesTo: String,
        list: MutableList<Ad>,
        listener: ISimpleSearchListener
    ) {
        listSimpleSearchResult.clear()
        list.forEach { ad ->
            val pf = try {
                if (priceFrom != "" || priceFrom != " ") priceFrom.toInt() else 0
            } catch (e: Exception) {
                0
            }
            val pt = try {
                if (priceTo != "" || priceTo != " ") priceTo.toInt() else Integer.MAX_VALUE
            } catch (e: Exception) {
                Integer.MAX_VALUE
            }
            val yf = try {
                if (yearFrom != "" || yearFrom != " ") yearFrom.toInt() else 0
            } catch (e: Exception) {
                0
            }
            val yt = try {
                if (yearTo != "" || yearTo != " ") yearTo.toInt() else Integer.MAX_VALUE
            } catch (e: Exception) {
                Integer.MAX_VALUE
            }


            val df = try {
                if (diametr_from != "" || diametr_from != " ") diametr_from.toInt() else 0
            } catch (e: Exception) {
                0
            }
            val dt = try {
                if (diametr_to != "" || diametr_to != " ") diametr_to.toInt() else Integer.MAX_VALUE
            } catch (e: Exception) {
                Integer.MAX_VALUE
            }
            val wf = try {
                if (width_from != "" || width_from != " ") width_from.toInt() else 0
            } catch (e: Exception) {
                0
            }
            val wt = try {
                if (width_to != "" || width_to != " ") width_to.toInt() else Integer.MAX_VALUE
            } catch (e: Exception) {
                Integer.MAX_VALUE
            }
            val hf = try {
                if (height_from != "" || height_from != " ") height_from.toInt() else 0
            } catch (e: Exception) {
                0
            }
            val ht = try {
                if (height_to != "" || height_to != " ") height_to.toInt() else Integer.MAX_VALUE
            } catch (e: Exception) {
                Integer.MAX_VALUE
            }

            val plf = try {
                if (placesFrom != "" || placesFrom != " ") placesFrom.toInt() else 0
            } catch (e: Exception) {
                0
            }
            val plt = try {
                if (placesTo != "" || placesTo != " ") placesTo.toInt() else Integer.MAX_VALUE
            } catch (e: Exception) {
                Integer.MAX_VALUE
            }

            if (ad.diameter == "")
                ad.diameter = "0"
            if (ad.width == "")
                ad.width = "0"
            if (ad.height == "")
                ad.height = "0"
            if (ad.places == "")
                ad.places = "0"


            if (ad.price in pf..pt) {
                if (ad.year in yf..yt) {
                    if (ad.diameter?.toInt() in df..dt) {
                        if (ad.width?.toInt() in wf..wt) {
                            if (ad.height?.toInt() in hf..ht) {
                                if (ad.places?.toInt() in plf..plt) {
                                    listSimpleSearchResult.add(ad)
                                }
                            }
                        }
                    }
                }
            }
        }

        listener.onSearchSimpleResult()
    }*/
}