package com.auto.autoads.model.admin

import android.net.Uri
import android.widget.Toast
import com.auto.autoads.model.ApplicationProvider
import com.auto.autoads.model.ad.AdManager
import com.auto.autoads.model.ad.AdManager.ADS
import com.auto.autoads.model.image.ImgeManager.bottom
import com.auto.autoads.model.image.ImgeManager.list
import com.auto.autoads.model.image.ImgeManager.top
import com.auto.autoads.model.utils.Ad
import com.auto.autoads.view.admin.IAdsAdminResult
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.io.File
import java.util.*

object AdminManager {
    fun onApproveAd(ad: Ad, callback: IAdsAdminResult) {
        ad.isApprove = true
        FirebaseDatabase.getInstance().getReference(ADS).child(ad.id?.toString() ?: return)
            .setValue(ad)
            .addOnSuccessListener {
                AdManager.getAllAds(callback)
            }
    }

    fun uploadImageForTopBanner(path: String) {
        uploadImage(top, path)
    }

    fun uploadImageForBottomBanner(path: String) {
        uploadImage(bottom, path)
    }

    fun uploadImageForListBanner(path: String) {
        uploadImage(list, path)
    }

    private fun uploadImage(key: String, path: String) {
        val ref =
            FirebaseStorage.getInstance().getReferenceFromUrl("gs://autoads-c8f9c.appspot.com")
                .child(Date().time.toString())
        val uploadTask = ref.putFile(Uri.fromFile(File(path)))
        uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> {
            return@Continuation ref.downloadUrl
        }).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                uploadBannerWasCompleted(task, key)
            }
        }
    }

    private fun uploadBannerWasCompleted(
        task: Task<Uri>,
        key: String
    ) {
        val downloadUri = task.result?.toString() ?: return
        FirebaseDatabase.getInstance().getReference(key)
            .child(Date().time.toString())
            .setValue(downloadUri)
        Toast.makeText(
            ApplicationProvider.instance,
            "Загрузка завершена",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun makeAdAsForward(ad: Ad) {
        FirebaseDatabase.getInstance().getReference("FORWARD")
            .child(ad.id?.toString().toString())
            .setValue(ad)
    }

    fun deleteAd(ad: Ad, message: () -> Unit, callback: IAdsAdminResult) {
        FirebaseDatabase.getInstance().getReference(ADS)
            .child(ad.id?.toString().toString())
            .removeValue().addOnSuccessListener {
                message.invoke()
                AdManager.getAllAds(callback)
            }
    }
}