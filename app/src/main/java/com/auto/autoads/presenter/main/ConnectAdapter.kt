package com.auto.autoads.presenter.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.auto.autoads.R
import com.auto.autoads.model.utils.Connect
import kotlinx.android.synthetic.main.item_connect.view.*

class ConnectAdapter(
    var connectVariant: MutableList<Connect>,
    private val callback: (Connect) -> Unit
) : RecyclerView.Adapter<ConnectAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.item_connect, parent, false)
        )

    override fun getItemCount(): Int = connectVariant.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val c = connectVariant[position]
        holder.apply {
            title.text = c.title
            icon.setImageResource(c.icon)

            itemView.setOnClickListener { callback.invoke(c) }
        }
    }

    class Holder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val title: TextView = rootView.tvTitleConnect
        val icon: ImageView = rootView.ivIconConnect
    }
}