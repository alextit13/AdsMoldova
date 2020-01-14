package com.auto.autoads.model.server

import com.auto.autoads.model.utils.Ad

interface IListAdListener {
    fun onListAdsResult(list:List<Ad>)
}