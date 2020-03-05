package com.auto.autoads.view.admin

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
import com.auto.autoads.model.utils.Ad
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_ad.view.*
import java.util.*

class AdminAdapter(
    var list: MutableList<Ad>,
    var clickCallback: (Ad) -> Unit,
    var longClickCallback: (Ad) -> Unit
) : RecyclerView.Adapter<AdminAdapter.AdViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder {
        return AdViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_ad,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AdViewHolder, position: Int) {
        val ad = list[position]
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
            itemView.setOnClickListener { clickCallback.invoke(ad) }
            itemView.setOnLongClickListener {
                longClickCallback.invoke(ad)
                true
            }
            if (ad.price == 0) {
                price.text = "Договор"
            } else {
                price.text = "$ " + ad.price
            }
            date.text = getDate(ad)
        }
    }

    private fun getDate(ad: Ad): String {
        val date = Date(ad.id ?: Date().time)

        return DateFormat.format("dd.MM.yyyy hh:mm", date).toString()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
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
}