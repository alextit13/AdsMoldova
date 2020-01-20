package com.auto.autoads.model.db

import com.auto.autoads.R
import com.auto.autoads.model.utils.Connect

object LocaleDbHolder {
    fun getConnectData(): MutableList<Connect> {
        return mutableListOf(
            Connect("Viber", "+37368255058", R.drawable.ic_viber, 1),
            Connect("WhatsApp", "+37368255058", R.drawable.ic_whatapp, 2),
            Connect("Email", "sellavto09@gmail.com", R.drawable.ic_email, 3)
        )
    }
}