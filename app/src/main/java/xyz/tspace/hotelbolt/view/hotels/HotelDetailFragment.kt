package xyz.tspace.hotelbolt.view.hotels

import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.qmuiteam.qmui.kotlin.onClick
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_hotel_detail.*
import kotlinx.android.synthetic.main.include_hotel_description.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.adapter.DetailImageBannerAdapter
import xyz.tspace.hotelbolt.adapter.TabPageAdapter
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.view.hotels.tab.HotelCommentTabFragment
import xyz.tspace.hotelbolt.view.hotels.tab.HotelDetailTabFragment
import xyz.tspace.hotelbolt.viewmodel.MainViewModel
import kotlin.math.abs

class HotelDetailFragment :
    BaseFragment<MainViewModel>(R.layout.fragment_hotel_detail, MainViewModel::class) {


    //viewPager2 详情tab页
    private val detailPage = HotelDetailTabFragment()


    //viewPager2 评论tab页
    private val commentPage = HotelCommentTabFragment()


    //在hotelList中点击lie'biao列表项后传递过来的参数：包含hotelId
    private val hotelDetailArgs: HotelDetailFragmentArgs by navArgs()


    override fun setStatusDarkMode(): Boolean? = true

    override fun initView() {
        setupToolbar()
        viewModel.fetchRoomComment("")
        viewModel.fetchHotelRoomInfo(hotelDetailArgs.hotelId)
        setupBanner()
        setupViewPager()


    }

    override fun initListener() {
        back_btn2.onClick { popBack() }
        alpha_btn1_back.onClick { popBack() }



    }

    override fun initObserver() {
        //观察房间信息获取，及时为图片图片提供图片源
        viewModel.hotelRoomLive.observe(requireActivity(), Observer {
            val picList = it[0].urlList
            if (picList != null) (bannerTop.adapter as DetailImageBannerAdapter).setDatas(picList)
            bannerTop.run {
                indicator = CircleIndicator(requireContext())
                start()
            }
        })
        //观察酒店信息获取
        viewModel.hotelListLive.observe(this, Observer {
            val iterator = it.listIterator()
            while (iterator.hasNext()) {
                val item = iterator.next()
                if (item.id == hotelDetailArgs.hotelId) {
                    hotelName_tv.text = item.hotelName
                    hotelRank_tv.text = item.hotelRank
                    ratingBar.rating = item.hotelSource
                    commentsNum_tv.text = item.hotelComment.toString()
                    location_tv.text = item.hotelLocation
                }
            }
        })
    }

    private fun setupViewPager() {
        val arrStr = getStringArray(R.array.tabTitle_hotelDetail)
        viewPager2.let {
            it.adapter = TabPageAdapter(this, detailPage, commentPage)
            it.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            TabLayoutMediator(
                tabLayout,
                it,
                true,
                TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                    tab.text = arrStr[position]
                }).attach()
        }
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