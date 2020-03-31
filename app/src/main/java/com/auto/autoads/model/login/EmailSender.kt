package com.auto.autoads.model.login

import com.auto.autoads.R
import com.auto.autoads.model.ApplicationProvider
import com.github.tntkhang.gmailsenderlibrary.GMailSender
import com.github.tntkhang.gmailsenderlibrary.GmailListener

object EmailSender {
    // https://myaccount.google.com/lesssecureapps
    fun sendEmailCode(
        code: String,
        email: String,
        callback: (String) -> Unit,
        error: (String) -> Unit
    ) {
        GMailSender.withAccount(
            "senderapplicator2020checkcodes@gmail.com",
            "SenderApplicator2020CheckCode")
            .withTitle("Код")
            .withSender(ApplicationProvider.instance.getString(R.string.app_name))
            .withBody("Не сообщайте никому, \n Ваш код проверки $code")
            .toEmailAddress(email)
            .withListenner(object : GmailListener {
                override fun sendSuccess() {
                    callback.invoke("Код успешно отправлен. Проверьте почту")
                }

                override fun sendFail(err: String?) {
                    err?.let {
                        error.invoke(it)
                    }
                }
            }).send()
    }
}