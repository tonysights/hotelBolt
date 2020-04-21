package xyz.tspace.hotelbolt.adapter

import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.qmuiteam.qmui.widget.QMUIRadiusImageView
import com.youth.banner.adapter.BannerAdapter
import xyz.tspace.hotelbolt.model.DataProvider

class DetailImageBannerAdapter(urlList: List<String>) :
    BannerAdapter<String, BAViewHolder>(urlList) {


    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BAViewHolder {
        return with(QMUIRadiusImageView(parent?.context)) {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            scaleType = ImageView.ScaleType.CENTER_CROP
            cornerRadius = 10
            BAViewHolder(this)
        }
    }

    override fun getItemCount(): Int {
        mDatas ?: DataProvider.getPreviewImgList()
        return mDatas.size
    }

    override fun onBindView(holder: BAViewHolder?, data: String?, position: Int, size: Int) {
        holder?.let {
            Glide.with(it.imgView).load(data).into(it.imgView)
        }
    }


}
