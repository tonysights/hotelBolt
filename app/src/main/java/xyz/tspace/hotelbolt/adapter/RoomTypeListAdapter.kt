package xyz.tspace.hotelbolt.adapter

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_room.view.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.model.RoomType

class RoomTypeListAdapter : ListAdapter<RoomType, WFViewHolder>(DiffConfig) {

    private var context: Context? = null
    private val inflater: LayoutInflater by lazy { LayoutInflater.from(context) }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WFViewHolder {
        if (context == null) context = parent.context
        return WFViewHolder(inflater.inflate(R.layout.item_room, parent, false))
    }

    override fun onBindViewHolder(holder: WFViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.itemView) {
            Glide.with(context).load(item.urlList?.get(0)).placeholder(R.drawable.city_eve)
                .into(roomTypeImg)
            roomTypeName.text = item.roomName
            val space = "18~${item.roomSpace}+㎡"
            roomSquare.text = space
            roomBedType.text = item.bedInfo
            roomBedFloor.text = "2-5楼"
            price.text = SpannableStringBuilder("￥起").apply {
//                setSpan(ForegroundColorSpan(Color.GRAY), 1, 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                setSpan(ForegroundColorSpan(Color.RED), 1, 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                insert(1, item.roomPrice.toString())
            }

        }

    }

    object DiffConfig : DiffUtil.ItemCallback<RoomType>() {
        override fun areItemsTheSame(oldItem: RoomType, newItem: RoomType): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: RoomType, newItem: RoomType): Boolean =
            oldItem.createTime == newItem.createTime
    }


}