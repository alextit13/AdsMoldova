package com.auto.autoads.view.dialog

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.ApplicationProvider
import com.auto.autoads.model.SpManager
import com.auto.autoads.model.ad.FavoritAdManager
import com.auto.autoads.model.db.DBGate
import com.auto.autoads.model.utils.Ad

fun showFavoritDialog(ad: Ad, activity: AppCompatActivity) {
    if (SpManager.getUser() == null) {
        Toast.makeText(
            ApplicationProvider.instance, "Зарегистрируйтесь или войдите в аккаунт"
            , Toast.LENGTH_LONG
        ).show()
        return
    }
    AlertDialog.Builder(activity)
        .setTitle(R.string.add_to_favorit)
        .setMessage(R.string.realy_add_to_favorit)
        .setPositiveButton(
            "Yes"
        ) { p0, _ ->
            addAdToFavorit(ad)
            p0.dismiss()
        }
        .setNegativeButton("Cancel") { p, _ ->
            p.dismiss()
        }
        .create().show()
}

fun addAdToFavorit(ad: Ad) {
    FavoritAdManager.addToFavorit(ad, {
        Toast.makeText(
            ApplicationProvider.instance, it
            , Toast.LENGTH_LONG
        ).show()
    }, {
        Toast.makeText(
            ApplicationProvider.instance, it
            , Toast.LENGTH_LONG
        ).show()
    })
    // DBGate.newInstance().insert(ad)
}
