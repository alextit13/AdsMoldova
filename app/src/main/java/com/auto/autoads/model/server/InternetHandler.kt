package com.auto.autoads.model.server

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import com.auto.autoads.model.ApplicationProvider
import com.auto.autoads.view.internet.InternetActivity

object InternetHandler {
    private var isLastConnect = true

    fun startCheckInternetConnection() {
        Thread(Runnable {
            while (true) {

                val isConnected = InternetConnection.checkConnection(
                    ApplicationProvider.instance
                )

                if (!isConnected && isLastConnect) {
                    isLastConnect = false
                    openOfflineActivity()
                }
                if (isConnected) {
                    isLastConnect = true
                }

                Thread.sleep(5000)
            }
        }).start()
    }

    private fun openOfflineActivity() {
        ApplicationProvider.instance.startActivity(Intent(
            ApplicationProvider.instance.applicationContext,
            InternetActivity::class.java
        ).apply {
            flags = FLAG_ACTIVITY_NEW_TASK
        })
    }
}