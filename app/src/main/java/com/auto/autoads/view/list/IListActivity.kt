package com.auto.autoads.view.list

import com.auto.autoads.model.utils.Ad

interface IListActivity {
    fun onInitAdapter(list: List<Ad>)
}