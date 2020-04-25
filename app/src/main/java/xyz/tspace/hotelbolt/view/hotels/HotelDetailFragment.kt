package xyz.tspace.hotelbolt.view.hotels

import android.view.View
import androidx.fragment.app.activityViewModels
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
import xyz.tspace.hotelbolt.model.Hotel
import xyz.tspace.hotelbolt.view.hotels.tab.HotelDetailTabFragment
import xyz.tspace.hotelbolt.view.hotels.tab.RoomTypeTabFragment
import xyz.tspace.hotelbolt.viewmodel.HotelViewModel
import kotlin.math.abs

class HotelDetailFragment : BaseFragment(R.layout.fragment_hotel_detail, true) {

    private val hotelViewModel by activityViewModels<HotelViewModel>()

    //viewPager2 详情tab页
    private val detailPage = HotelDetailTabFragment()


    //viewPager2 评论tab页
    private val roomTypePage = RoomTypeTabFragment()


    //在hotelList中点击列表项后传递过来的参数：包含hotelId
    private val hotelDetailArgs: HotelDetailFragmentArgs by navArgs()


    override fun initView() {

        updateData()
        setupToolbar()
        setupBanner()
        setupViewPager()

    }

    override fun initListener() {
        back_btn2.onClick { popBack() }
        alpha_btn1_back.onClick { popBack() }


    }

    override fun initObserver() {
        hotelViewModel.hotelListLive.observe(this, Observer {
            setupData(it)
        })
        hotelViewModel.roomTypeListLive.observe(this, Observer {
            if (it.isNotEmpty()) {
                val urlList = it[0].urlList
                bannerTop.run {

                    if (urlList != null) {
                        adapter = DetailImageBannerAdapter(urlList)
                        indicator = CircleIndicator(requireContext())
                    }
                }
            }
        })
    }

    //获取酒店房间信息和相关评论
    private fun updateData() {
        hotelViewModel.fetchRoomListByHotelId(hotelDetailArgs.hotelId)
        hotelViewModel.fetchRoomCommentByRoomTypeId("1")
    }

    //将数据提供给组件
    private fun setupData(list: List<Hotel>) {
        val iterator = list.listIterator()
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
    }


    private fun setupViewPager() {
        val arrStr = getStringArray(R.array.tabTitle_hotelDetail)
        viewPager2.let {
            it.adapter = TabPageAdapter(this, detailPage, roomTypePage)
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
                toolbarDetail?.visibility = View.INVISIBLE
            } else toolbarDetail?.visibility = View.VISIBLE
        })
    }
}