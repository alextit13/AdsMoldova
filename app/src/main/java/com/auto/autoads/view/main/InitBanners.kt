package com.auto.autoads.view.main

import android.content.Intent
import com.auto.autoads.model.ad.AdManager
import com.auto.autoads.view.detail.DetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

fun initBanners(activity: MainActivity) {
    if (AdManager.listFavorits.isEmpty()) return
    for (i in 0 until AdManager.listFavorits.size) {
        try {
            val ad = AdManager.listFavorits[i]
            when (i) {
                0 -> {
                    activity.rl_1.setOnClickListener {
                        DetailActivity.ad = ad
                        activity.startActivity(Intent(activity, DetailActivity::class.java))
                    }
                    activity.title_ad_1.text = ad.category
                    if (ad.linkImages?.isNotEmpty() == true)
                        Picasso.get().load(ad.linkImages?.first()).into(activity.iv_ad_1)

                }
                1 -> {
                    activity.rl_2.setOnClickListener {
                        DetailActivity.ad = ad
                        activity.startActivity(Intent(activity, DetailActivity::class.java))
                    }
                    activity.title_ad_2.text = ad.category
                    if (ad.linkImages?.isNotEmpty() == true)
                        Picasso.get().load(ad.linkImages?.first()).into(activity.iv_ad_2)
                }
                2 -> {
                    activity.rl_3.setOnClickListener {
                        DetailActivity.ad = ad
                        activity.startActivity(Intent(activity, DetailActivity::class.java))
                    }
                    activity.title_ad_3.text = ad.category
                    if (ad.linkImages?.isNotEmpty() == true)
                        Picasso.get().load(ad.linkImages?.first()).into(activity.iv_ad_3)
                }
                3 -> {
                    activity.rl_4.setOnClickListener {
                        DetailActivity.ad = ad
                        activity.startActivity(Intent(activity, DetailActivity::class.java))
                    }
                    activity.title_ad_4.text = ad.category
                    if (ad.linkImages?.isNotEmpty() == true)
                        Picasso.get().load(ad.linkImages?.first()).into(activity.iv_ad_4)
                }
                4 -> {
                    activity.rl_5.setOnClickListener {
                        DetailActivity.ad = ad
                        activity.startActivity(Intent(activity, DetailActivity::class.java))
                    }
                    activity.title_ad_5.text = ad.category
                    if (ad.linkImages?.isNotEmpty() == true)
                        Picasso.get().load(ad.linkImages?.first()).into(activity.iv_ad_5)
                }
                5 -> {
                    activity.rl_6.setOnClickListener {
                        DetailActivity.ad = ad
                        activity.startActivity(Intent(activity, DetailActivity::class.java))
                    }
                    activity.title_ad_6.text = ad.category
                    if (ad.linkImages?.isNotEmpty() == true)
                        Picasso.get().load(ad.linkImages?.first()).into(activity.iv_ad_6)
                }
                6 -> {
                    activity.rl_7.setOnClickListener {
                        DetailActivity.ad = ad
                        activity.startActivity(Intent(activity, DetailActivity::class.java))
                    }
                    activity.title_ad_7.text = ad.category
                    if (ad.linkImages?.isNotEmpty() == true)
                        Picasso.get().load(ad.linkImages?.first()).into(activity.iv_ad_7)
                }
                7 -> {
                    activity.rl_8.setOnClickListener {
                        DetailActivity.ad = ad
                        activity.startActivity(Intent(activity, DetailActivity::class.java))
                    }
                    activity.title_ad_8.text = ad.category
                    if (ad.linkImages?.isNotEmpty() == true)
                        Picasso.get().load(ad.linkImages?.first()).into(activity.iv_ad_8)
                }
            }

        } catch (e: Exception) {

        }
    }
}