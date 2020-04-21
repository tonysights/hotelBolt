package xyz.tspace.hotelbolt.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.qmuiteam.qmui.widget.QMUIRadiusImageView
import com.youth.banner.adapter.BannerAdapter
import xyz.tspace.hotelbolt.model.DataProvider
import xyz.tspace.hotelbolt.model.PixabayImage

class ImageBannerAdapter(datas: List<PixabayImage>?) :
    BannerAdapter<PixabayImage, BAViewHolder>(datas) {


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

    override fun onBindView(holder: BAViewHolder?, data: PixabayImage?, position: Int, size: Int) {
        holder?.let {
            Glide.with(it.imgView).load(data?.previewURL).into(it.imgView)
        }
    }

    override fun getItemCount(): Int {
        mDatas ?: DataProvider.getPreviewImgList()
        return mDatas.size
    }


}

class BAViewHolder(val imgView: QMUIRadiusImageView) : RecyclerView.ViewHolder(imgView)