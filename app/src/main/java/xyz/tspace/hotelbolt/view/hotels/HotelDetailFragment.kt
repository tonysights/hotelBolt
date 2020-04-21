package xyz.tspace.hotelbolt.view.hotels

import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.AppBarLayout
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_hotel_detail.*
import kotlinx.android.synthetic.main.include_hotel_description.*
import kotlinx.android.synthetic.main.include_hotel_info.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.adapter.DetailImageBannerAdapter
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.viewmodel.MainViewModel
import kotlin.math.abs

class HotelDetailFragment :
    BaseFragment<MainViewModel>(R.layout.fragment_hotel_detail, MainViewModel::class) {

    private val hotelDetailArgs: HotelDetailFragmentArgs by navArgs()
    override fun setStatusDarkMode(): Boolean? = true


    override fun initView() {
        setupToolbar()
        viewModel.fetchHotelRoomInfo(hotelDetailArgs.hotelId)
        setupBanner()


    }

    override fun initListener() {
        toolbar.setNavigationOnClickListener { popBack() }

    }

    override fun initObserver() {
        viewModel.hotelRoomLive.observe(requireActivity(), Observer {
            val picList = it[0].urlList
            if (picList != null) (bannerTop.adapter as DetailImageBannerAdapter).setDatas(picList)
            hotelIntro_tv.text = it[0].roomDetails
            bannerTop.run {
                indicator = CircleIndicator(requireContext())
                start()
            }

        })
        viewModel.hotelListLive.observe(requireActivity(), Observer {
            val iterator = it.listIterator()
            while (iterator.hasNext()) {
                val item = iterator.next()
                if (item.id == hotelDetailArgs.hotelId) {
                    hotelRank_tv.text = item.hotelRank
                    ratingBar.rating = item.hotelSource
                    commentsNum_tv.text = item.hotelComment.toString()
                    location_tv.text = item.hotelLocation
                }
            }
        })
    }

    private fun setupBanner() {
        bannerTop.run {
            indicator = CircleIndicator(requireContext())
            adapter =
                DetailImageBannerAdapter(listOf("https://gss0.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/810a19d8bc3eb135299416fda61ea8d3fd1f4420.jpg"))
            setIndicatorNormalColor(getColor(R.color.default_BookPage_backgroundColor))
            setIndicatorSelectedColor(getColor(R.color.qmui_config_color_50_white))
            setDelayTime(2700)
        }
    }

    private fun setupToolbar() {
        hotelDesContainer.radius = 11
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val offset = abs(verticalOffset)
            if (offset < appBarLayout.totalScrollRange / 2) {
                toolbarDetail.visibility = View.INVISIBLE
            } else toolbarDetail.visibility = View.VISIBLE


        })
    }
}