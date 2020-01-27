package com.auto.autoads.view.admin

import androidx.recyclerview.widget.RecyclerView
import com.auto.autoads.model.utils.Ad
import com.auto.autoads.view.list.IListItemClickListener
import com.auto.autoads.view.list.ListResultAdapter

class AdminAdapter(
    var list: MutableList<Ad>,
    callback: IListItemClickListener,
    val longCallback: ILongClickListener
) : ListResultAdapter(list, callback, {
    // todo long click
}) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.setOnLongClickListener {
            longCallback.onLongClick(list[position])
            true
        }
    }
}