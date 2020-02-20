package com.auto.autoads.view.list

import android.graphics.Color
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.auto.autoads.R
import com.auto.autoads.model.image.ImgeManager
import com.auto.autoads.model.utils.Ad
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_ad.view.*
import kotlinx.android.synthetic.main.item_ad_banner.view.*
import java.util.*

open class ListResultAdapter(
    private val items: List<Ad>,
    private var listener: IListItemClickListener,
    private val favoritCallback: (Ad) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int) = if (position % 25 == 0 && position != 0)
        BannerPosition.Banner.type else BannerPosition.Ad.type

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            BannerPosition.Ad.type -> return AdViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_ad,
                    parent,
                    false
                )
            )
            else -> return AdBannerViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_ad_banner,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ad: Ad = items[position]
        when (holder.itemViewType) {
            BannerPosition.Ad.type -> {
                (holder as AdViewHolder).apply {
                    if (ad.marka != "" || ad.model != "")
                        title.text = ad.marka + " " + ad.model
                    else
                        title.text = ad.category
                    description.text = ad.subscription
                    if (ad.linkImages?.isNotEmpty()!!)
                        Picasso.get().load(ad.linkImages?.get(0))
                            .placeholder(R.drawable.ic_test_logo).into(
                            ivAd
                        )
                    if (ad.isApprove) {
                        ivApprove.setCardBackgroundColor(Color.GREEN)
                    } else {
                        ivApprove.setCardBackgroundColor(Color.RED)
                    }
                    itemView.setOnClickListener { listener.onItemAdClick(ad) }
                    itemView.setOnLongClickListener {
                        listener.onLongClick(ad)
                        true
                    }
                    if (ad.price == 0) {
                        price.text = "Договор"
                    } else {
                        price.text = "$ " + ad.price
                    }
                    date.text = getDate(ad)
                    favorit.setOnClickListener { favoritCallback.invoke(ad) }
                }
            }
            else -> {
                (holder as AdBannerViewHolder).apply {
                    val path = ImgeManager.newInstance().bannerInListImageUrl
                    if (path != "") {
                        Picasso.get().load(path).into(baner)
                    }
                }
            }
        }
    }

    private fun getDate(ad: Ad): String {
        val date = Date(ad.id ?: Date().time)

        return DateFormat.format("dd.MM.yyyy hh:mm", date).toString()
    }

    class AdViewHolder
        (viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val title: TextView = viewItem.tvTitleAd
        val description: TextView = viewItem.tvDescription
        val ivAd: ImageView = itemView.ivAd
        val ivApprove: CardView = itemView.ivApprove
        val price: TextView = itemView.tvPrice
        val date: TextView = itemView.date
        val favorit: ImageView = itemView.ivAddToFavorit
    }

    class AdBannerViewHolder
        (viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val itemFl: FrameLayout = viewItem.flAdListBanner
        val baner: ImageView = viewItem.listItemBanner
    }

    enum class BannerPosition(val type: Int) {
        Banner(0),
        Ad(1)
    }
}