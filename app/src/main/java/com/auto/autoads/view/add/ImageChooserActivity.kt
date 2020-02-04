package com.auto.autoads.view.add

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auto.autoads.R
import com.auto.autoads.model.ad.AdManager
import com.auto.autoads.model.ad.AdManager.clearData
import com.auto.autoads.model.ad.IadManagerCallback
import com.auto.autoads.model.utils.DataHandler.adForSend
import com.auto.autoads.model.utils.DataHandler.listCheckImages
import com.auto.autoads.view.main.MainActivity
import com.jaiselrahman.filepicker.activity.FilePickerActivity
import com.jaiselrahman.filepicker.config.Configurations
import com.jaiselrahman.filepicker.model.MediaFile
import kotlinx.android.synthetic.main.activity_add_images.*
import java.io.File
import java.io.FileInputStream

class ImageChooserActivity : AppCompatActivity(), IadManagerCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_images)
    }

    fun onClick1(view: View) {
        try {
            iv_1.setImageResource(R.drawable.ic_add_a_photo)
            listCheckImages.removeAt(0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onClick2(view: View) {
        try {
            iv_2.setImageResource(R.drawable.ic_add_a_photo)
            listCheckImages.removeAt(1)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onClick3(view: View) {
        try {
            iv_3.setImageResource(R.drawable.ic_add_a_photo)
            listCheckImages.removeAt(2)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onClick4(view: View) {
        try {
            iv_4.setImageResource(R.drawable.ic_add_a_photo)
            listCheckImages.removeAt(3)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onClick5(view: View) {
        try {
            iv_5.setImageResource(R.drawable.ic_add_a_photo)
            listCheckImages.removeAt(4)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun addImage(view: View) {
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
        }, 991)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val files = data?.getParcelableArrayListExtra<MediaFile>(FilePickerActivity.MEDIA_FILES)

            if (files?.isEmpty() != false) return

            val fileSize = files.first()?.size?.div(1024) ?: 0L // in kb
            if (fileSize > 2000) {
                Toast.makeText(this, "Изображение должно быть менее 2 МБ", Toast.LENGTH_LONG).show()
                return
            }


            onPickImageResult(files[0])
        }
    }

    private fun onPickImageResult(get: MediaFile?) {
        listCheckImages.add(get?.path ?: "")
        showAllImages()
    }

    private fun showAllImages() {
        var iterator = 1
        try {
            listCheckImages.forEach {
                val bitmap: Bitmap?
                val f = File(it)
                val options = BitmapFactory.Options()
                options.inPreferredConfig = Bitmap.Config.ARGB_8888

                bitmap = BitmapFactory.decodeStream(FileInputStream(f), null, options)
                when (iterator) {
                    1 -> iv_1.setImageBitmap(bitmap)
                    2 -> iv_2.setImageBitmap(bitmap)
                    3 -> iv_3.setImageBitmap(bitmap)
                    4 -> iv_4.setImageBitmap(bitmap)
                    5 -> iv_5.setImageBitmap(bitmap)
                }

                iterator++
            }
            iterator = 0
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun complete(view: View) {
        showProgress()
        if (adForSend == null) {
            dismissProgress()
            Toast.makeText(
                this, "Что-то пошло не так. Обратитесь в службу поддержки", Toast.LENGTH_SHORT
            ).show()
            return
        }
        adForSend?.let { AdManager.sendAdToServer(it, this) }
    }

    override fun onAdSendSuccess() {
        Toast.makeText(
            this, "Объявление было успешно отправлено", Toast.LENGTH_SHORT
        ).show()
        dismissProgress()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onAdSendError() {
        dismissProgress()
        Toast.makeText(
            this, "Что-то пошло не так. Обратитесь в службу поддержки", Toast.LENGTH_SHORT
        ).show()
    }

    private fun showProgress(){
        flProgress.visibility = View.VISIBLE
        llContainer.visibility = View.GONE
    }

    private fun dismissProgress(){
        flProgress.visibility = View.GONE
        llContainer.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        clearData()
        super.onBackPressed()
    }
}