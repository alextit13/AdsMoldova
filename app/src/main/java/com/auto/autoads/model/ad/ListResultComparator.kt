package com.auto.autoads.model.ad

import com.auto.autoads.model.utils.Ad

fun MutableList<Ad>.compareByDate(): MutableList<Ad> {
    sortedWith(compareBy { it.id })
    return this.reversed().toMutableList()
}