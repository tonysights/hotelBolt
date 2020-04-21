package xyz.tspace.hotelbolt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import kotlinx.android.synthetic.main.item_comment.view.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.model.Appraise

class CommentListAdapter :
    ListAdapter<Appraise, WFViewHolder>(DiffCallback) {

    private var context: Context? = null

    private val inflater: LayoutInflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WFViewHolder {
        if (context == null) context = parent.context
        return WFViewHolder(inflater.inflate(R.layout.item_comment, parent, false))
    }

    override fun onBindViewHolder(holder: WFViewHolder, position: Int) {

        val item = getItem(position)
        with(holder.itemView) {
            Glide.with(this).load(R.drawable.ic_person).into(userAvatar)
            commentContent.text = item.content
            commentDate.text = item.createTime
            username.text = item.username

            qmuiConstraintLayout.onlyShowBottomDivider(
                25,
                25,
                1,
                R.color.qmui_config_color_gray_9
            )

        }

    }

    fun dp2px(dp: Int) = QMUIDisplayHelper.dp2px(context, dp)

    object DiffCallback : DiffUtil.ItemCallback<Appraise>() {
        override fun areItemsTheSame(oldItem: Appraise, newItem: Appraise) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Appraise, newItem: Appraise) =
            oldItem.id == newItem.id && oldItem.content == newItem.content
    }
}

