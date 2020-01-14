package com.auto.autoads.view.list

import com.auto.autoads.model.utils.Ad

interface IListItemClickListener {
    fun onItemAdClick(ad: Ad)
    fun onLongClick(ad: Ad)
}