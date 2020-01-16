package com.auto.autoads.view.admin

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.admin.AdminManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.jaiselrahman.filepicker.activity.FilePickerActivity
import com.jaiselrahman.filepicker.config.Configurations
import com.jaiselrahman.filepicker.model.MediaFile
import kotlinx.android.synthetic.main.activity_admin.*

class AdminActivity : AppCompatActivity() {

    companion object {
        const val top = 22
        const val bottom = 23
        const val list = 24
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        FirebaseDatabase.getInstance().getReference("")
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {}

                @SuppressLint("SetTextI18n")
                override fun onDataChange(p0: DataSnapshot) {
                    runOnUiThread {
                        val num = p0.childrenCount
                        tvIterator.text = "Открытий приложения: $num"
                    }
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val files = data?.getParcelableArrayListExtra<MediaFile>(FilePickerActivity.MEDIA_FILES)
            if (files?.isEmpty() != false) return

            AlertDialog.Builder(this).setTitle("Изображение")
                .setMessage("Загрузить выбранное изображение?")
                .setPositiveButton("Да") { p0, _ ->
                    when (requestCode) {
                        top -> {
                            AdminManager.uploadImageForTopBanner(files[0]?.path ?: "")
                        }
                        bottom -> {
                            AdminManager.uploadImageForBottomBanner(files[0]?.path ?: "")
                        }
                        list -> {
                            AdminManager.uploadImageForListBanner(files[0]?.path ?: "")
                        }
                    }
                    p0?.dismiss()
                }
                .setNeutralButton(
                    "Отмена"
                ) { p0, _ -> p0?.dismiss() }.show()
        }
    }

    fun adsApprove(view: View) {
        startActivity(Intent(this, AdminListApproveActivity::class.java))
    }

    fun topBanner(view: View) {
        startImageChooser(top)
    }

    fun bottomBanner(view: View) {
        startImageChooser(bottom)
    }

    private fun startImageChooser(requestCode: Int) {
        startActivityForResult(Intent(this, FilePickerActivity::class.java).apply {
            putExtra(
                FilePickerActivity.CONFIGS, Configurations.Builder()
                    .setCheckPermission(true)
                    .setShowImages(true)
                    .enableImageCapture(true)
                    .setMaxSelection(1)
                    .setSkipZeroSizeFiles(true)
                    .build()
            )
        }, requestCode)
    }

    fun listBanner(view: View) {
        startImageChooser(list)
    }

    fun dataLoginAdmin(view: View) {
        startActivity(Intent(this, LoginDataAdminActivity::class.java))
    }
}