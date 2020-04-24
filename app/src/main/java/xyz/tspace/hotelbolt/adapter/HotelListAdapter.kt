package xyz.tspace.hotelbolt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.item_list_hotel.view.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.model.Hotel
import xyz.tspace.hotelbolt.view.hotels.HotelListFragmentDirections

class HotelListAdapter :
    ListAdapter<Hotel, HotelListViewHolder>(diffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelListViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_list_hotel, parent, false)
        val holder = HotelListViewHolder(itemView)
        itemView.apply {
            favButton.onClick {
                if (it.tag != null) {
                    val res = if (it.tag == R.drawable.ic_favorite_full_24dp)
                        R.drawable.ic_favorite_border_white_24dp else R.drawable.ic_favorite_full_24dp
                    it.tag = res
                } else it.tag = R.drawable.ic_favorite_full_24dp
                val temp = it.tag
                Glide.with(it).load(it).into(favButton)
            }
            this.onClick {
                val hotelListAction =
                    HotelListFragmentDirections.actionHotelListFragmentToHotelDetailFragment()
                val id = it.tag
                hotelListAction.hotelId = if (id is String) id else "1"
                it.findNavController().navigate(hotelListAction)
            }
            priceLayout.radius = 5
        }
        return holder
    }

    override fun onBindViewHolder(holder: HotelListViewHolder, position: Int) {

        val item = getItem(position)
        holder.itemView.run {
            tag = item.id
            hotelName_tv.text = item.hotelName
            star_tv.text = item.hotelRank
            rating.rating = item.hotelSource
            location_tv.text = item.hotelLocation
            Glide.with(this).load(item.hotelPic).placeholder(R.drawable.city_eve).into(hotelImage)

        }
    }


    object diffCallback : DiffUtil.ItemCallback<Hotel>() {

        override fun areItemsTheSame(oldItem: Hotel, newItem: Hotel): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Hotel, newItem: Hotel): Boolean =
            oldItem.id == newItem.id && oldItem.hotelName == newItem.hotelName
    }
}

class HotelListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)