package com.auto.autoads.view.list

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.auto.autoads.R
import com.auto.autoads.model.image.ImgeManager
import com.auto.autoads.model.utils.Ad
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_ad.view.*
import java.util.*

open class ListResultAdapter(
    private val items: List<Ad>,
    private var listener: IListItemClickListener,
    private val favoritCallback: (Ad) -> Unit
) :
    RecyclerView.Adapter<ListResultAdapter.AdViewHolder>() {

    override fun getItemViewType(position: Int) = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder =
        AdViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_ad,
                parent,
                false
            )
        )

    override fun getItemCount() = items.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AdViewHolder, position: Int) {
        val ad: Ad = items[position]
        holder.apply {
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
            itemView.cardViewItemAdRoot.setOnClickListener { listener.onItemAdClick(ad) }
            itemView.cardViewItemAdRoot.setOnLongClickListener {
                listener.onLongClick(ad)
                true
            }
            if (ad.price == 0) {
                price.text = "Договор"
            } else {
                price.text = "$ ${ad.price}"
            }
            date.text = getDate(ad)
            favorit.setOnClickListener { favoritCallback.invoke(ad) }

            if (position != 0 && position % 25 == 0) {
                holder.itemView.ivItemBanner.apply {
                    visibility = View.VISIBLE
                    val listUrls = ImgeManager.bannerInListImageUrl
                    if (listUrls.isEmpty()) return

                    val randomUrl = listUrls[Random().nextInt(listUrls.size)]
                    Picasso.get().load(randomUrl).into(this)
                }

            } else {
                holder.itemView.ivItemBanner
                    .visibility = View.GONE
            }
        }
    }

    private fun getDate(ad: Ad): String {
        val date = Date(ad.id ?: Date().time)

        return DateFormat.format("dd.MM.yyyy hh:mm", date).toString()
    }

    class AdViewHolder(
        viewItem: View
    ) : RecyclerView.ViewHolder(viewItem) {
        val title: TextView = viewItem.tvTitleAd
        val description: TextView = viewItem.tvDescription
        val ivAd: ImageView = itemView.ivAd
        val ivApprove: CardView = itemView.ivApprove
        val price: TextView = itemView.tvPrice
        val date: TextView = itemView.date
        val favorit: ImageView = itemView.ivAddToFavorit
    }
}