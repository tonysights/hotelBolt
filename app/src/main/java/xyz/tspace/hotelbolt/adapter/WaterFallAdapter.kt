package xyz.tspace.hotelbolt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import kotlinx.android.synthetic.main.item_home_waterfall.view.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.WaterFallCell
import kotlin.random.Random

class WaterFallAdapter() : ListAdapter<WaterFallCell, WFViewHolder>(diffCallback) {

    //context
    private var _context: Context? = null
    val context get() = _context

    //inflater
    private val inflater by lazy { LayoutInflater.from(context) }

    //params
    val EDGE_SCREEN_MARGIN by lazy { dp2px(15) }
    val INNER_MARGIN by lazy { dp2px(6) }
    val ITEM_HEIGHT by lazy { dp2px(260) }
    val ITEM_HEIGHT_SP by lazy { dp2px(200) }
    val ITEM_MARGIN_TOP by lazy { dp2px(9) }
    val ITEM_HEIGHT_RANDOM by lazy { dp2px(Random(10).nextInt(300 - 180 + 1) + 180) }

    //layoutParams
    val paramRight by lazy {
        constraintLayoutParams(ITEM_HEIGHT).apply {
            setMargins(INNER_MARGIN, ITEM_MARGIN_TOP, EDGE_SCREEN_MARGIN, 0)
        }
    }
    val paramLeft by lazy {
        constraintLayoutParams(ITEM_HEIGHT).apply {
            setMargins(EDGE_SCREEN_MARGIN, ITEM_MARGIN_TOP, INNER_MARGIN, 0)
        }
    }
    val paramSP by lazy {
        constraintLayoutParams(ITEM_HEIGHT_SP).apply {
            setMargins(EDGE_SCREEN_MARGIN, ITEM_MARGIN_TOP, INNER_MARGIN, 0)
        }
    }
    val paramsRandomLeft by lazy {
        constraintLayoutParams(ITEM_HEIGHT_RANDOM).apply {
            setMargins(EDGE_SCREEN_MARGIN, ITEM_MARGIN_TOP, INNER_MARGIN, 0);
        }
    }
    val paramsRandomRight by lazy {
        constraintLayoutParams(ITEM_HEIGHT_RANDOM).apply {
            setMargins(INNER_MARGIN, ITEM_MARGIN_TOP, EDGE_SCREEN_MARGIN, 0)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WFViewHolder {
        _context ?: parent.context
        val v = inflater.inflate(R.layout.item_home_waterfall, parent, false)
        return WFViewHolder(v)
    }



    override fun onBindViewHolder(holder: WFViewHolder, position: Int) {

        val item = getItem(position)


        with(holder.itemView.container) {
            val randomInt = Random(123).nextInt(8) + 1
            layoutParams = when {
                position == 0 -> paramSP
                position % 2 == 0 && position % randomInt == 0 -> paramsRandomLeft
                position % 2 == 0 && position % randomInt != 0 -> paramLeft
                position % 2 != 0 && position % randomInt == 0 -> paramsRandomRight
                position % 2 != 0 && position % randomInt != 0 -> paramRight
                else -> paramSP
            }
            radius = 18
        }


        with(holder.itemView) {
            title.text = item.title
            subtitle.text = item.subTitle
            detail.text = item.detail
            Glide.with(context).load(R.drawable.city_eve).into(qmuImg)
        }


    }

    object diffCallback : DiffUtil.ItemCallback<WaterFallCell>() {
        override fun areItemsTheSame(oldItem: WaterFallCell, newItem: WaterFallCell): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: WaterFallCell, newItem: WaterFallCell): Boolean =
            oldItem.title == newItem.title && oldItem.subTitle == newItem.subTitle
    }

    private fun dp2px(dp: Int) = QMUIDisplayHelper.dp2px(context, dp)
    private fun constraintLayoutParams(px: Int) =
        ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, px)

}

class WFViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)