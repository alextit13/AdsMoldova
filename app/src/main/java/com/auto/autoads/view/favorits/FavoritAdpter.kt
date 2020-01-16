package com.auto.autoads.view.favorits

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

class FavoritAdpter(
    var items: MutableList<Ad>,
    var callback: (Ad) -> Unit,
    var longClick: (Ad) -> Unit
) : RecyclerView.Adapter<FavoritAdpter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_ad, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val ad = items[position]
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
            itemView.setOnClickListener { callback.invoke(ad) }

            price.text = "$ " + ad.price
            date.text = getDate(ad)
            favorit.visibility = View.GONE

            itemView.setOnLongClickListener {
                longClick.invoke(ad)
                true
            }
        }
    }

    private fun getDate(ad: Ad): String {
        val date = Date(ad.id ?: Date().time)

        return DateFormat.format("dd.MM.yyyy hh:mm", date).toString()
    }

    class Holder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val title: TextView = rootView.tvTitleAd
        val description: TextView = rootView.tvDescription
        val ivAd: ImageView = rootView.ivAd
        val ivApprove: CardView = rootView.ivApprove
        val price: TextView = rootView.tvPrice
        val date: TextView = rootView.date
        val favorit: ImageView = rootView.ivAddToFavorit
    }
}