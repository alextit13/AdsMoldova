package com.auto.autoads.model.ad

import com.auto.autoads.model.SpManager
import com.auto.autoads.model.ad.AdManager.ADS
import com.auto.autoads.model.utils.Ad
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object FavoritAdManager {

    private const val favorit_ads = "favorit_ads"

    fun getAllFavoridAds(
        callback: (MutableList<Ad>) -> Unit,
        error: (String) -> Unit
    ) {
        val currencUser = SpManager.getUser() ?: return

        val list = mutableListOf<String>()
        FirebaseDatabase.getInstance().getReference(currencUser.email.substringBefore("."))
            .child(favorit_ads)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    error.invoke(p0.message)
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        list.add(it.getValue(String::class.java) ?: "-1")
                    }
                    getAds(callback, list, error)
                }
            })
    }

    private fun getAds(
        callback: (MutableList<Ad>) -> Unit,
        listIds: MutableList<String>,
        error: (String) -> Unit
    ) {
        val listAds = mutableListOf<Ad>()
        FirebaseDatabase.getInstance().getReference(ADS)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    error.invoke(p0.message)
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        val ad = it.getValue(Ad::class.java) ?: Ad()
                        if (ad.isApprove && listIds.contains(ad.id.toString())) {
                            listAds.add(ad)
                        }
                    }
                    callback.invoke(listAds)
                }
            })
    }

    fun removeAdFromFavorit(
        adId: Long,
        success: (String) -> Unit,
        error: (String) -> Unit
    ) {
        val currencUser = SpManager.getUser() ?: return

        FirebaseDatabase.getInstance().getReference(currencUser.email.substringBefore("."))
            .child(favorit_ads)
            .child(adId.toString())
            .removeValue().addOnSuccessListener {
                success.invoke("Удалено")
            }
            .addOnFailureListener {
                error.invoke(it.message ?: "Ошибка удаления")
            }
    }

    fun addToFavorit(
        ad: Ad,
        success: (String) -> Unit,
        error: (String) -> Unit
    ) {
        val currencUser = SpManager.getUser() ?: return

        FirebaseDatabase.getInstance().getReference(currencUser.email.substringBefore("."))
            .child(favorit_ads)
            .child(ad.id.toString())
            .setValue(ad.id.toString())
            .addOnSuccessListener {
                success.invoke("Добавлено в избранное")
            }
            .addOnFailureListener {
                error.invoke("Ошибка добавления")
            }

    }
}