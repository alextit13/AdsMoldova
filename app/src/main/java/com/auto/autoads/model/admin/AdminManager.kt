package com.auto.autoads.model.admin

import android.net.Uri
import android.widget.Toast
import com.auto.autoads.model.ApplicationProvider
import com.auto.autoads.model.ad.AdManager.ADS
import com.auto.autoads.model.image.ImgeManager.bottom
import com.auto.autoads.model.image.ImgeManager.list
import com.auto.autoads.model.image.ImgeManager.top
import com.auto.autoads.model.utils.Ad
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.io.File

object AdminManager {
    fun onApproveAd(ad: Ad) {
        ad.isApprove = true
        FirebaseDatabase.getInstance().getReference(ADS).child(ad.id?.toString() ?: return)
            .setValue(ad)
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
                .child(key)
        val uploadTask = ref.putFile(Uri.fromFile(File(path)))
        uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            return@Continuation ref.downloadUrl
        }).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result?.toString()
                FirebaseDatabase.getInstance().getReference(key).setValue(downloadUri)
                Toast.makeText(
                    ApplicationProvider.instance,
                    "Загрузка завершена",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun makeAdAsForward(ad: Ad) {
        FirebaseDatabase.getInstance().getReference("FORWARD")
            .child(ad.id?.toString().toString())
            .setValue(ad)
    }

    fun deleteAd(ad: Ad, message: () -> Unit) {
        FirebaseDatabase.getInstance().getReference(ADS)
            .child(ad.id?.toString().toString())
            .removeValue().addOnSuccessListener { message.invoke() }
    }
}