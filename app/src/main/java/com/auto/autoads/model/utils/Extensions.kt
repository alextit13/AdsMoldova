package com.auto.autoads.model.utils

import android.text.Editable

fun Editable.makeDef(): String {
    return if (toString() == "") "0"
    else toString()
}