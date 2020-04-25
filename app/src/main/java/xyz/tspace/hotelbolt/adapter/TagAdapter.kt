package xyz.tspace.hotelbolt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kotlinx.android.synthetic.main.item_room_type_tag.view.*
import xyz.tspace.hotelbolt.R

class TagAdapter(private val clickListener: View.OnClickListener) :
    ListAdapter<String, WFViewHolder>(DiffCallback) {

    private var context: Context? = null
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WFViewHolder {
        if (context == null) context = parent.context
        return WFViewHolder(inflater.inflate(R.layout.item_room_type_tag, parent, false))
    }

    override fun onBindViewHolder(holder: WFViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.itemView.roomtypeTag) {
            text = item
            setOnClickListener(clickListener)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem
    }
}