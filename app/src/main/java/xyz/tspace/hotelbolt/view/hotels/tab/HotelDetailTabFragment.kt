package xyz.tspace.hotelbolt.view.hotels.tab

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.include_hotel_info.view.*
import kotlinx.android.synthetic.main.vp_hotel_detail.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.viewmodel.HotelViewModel

class HotelDetailTabFragment : BaseFragment(R.layout.vp_hotel_detail) {
    private val hotelViewModel by activityViewModels<HotelViewModel>()

    override fun initView() {


    }

    override fun initListener() {

    }

    override fun initObserver() {

        hotelViewModel.roomListLive.observe(this, Observer {
            val introText = it[0].roomDetails
            if (introText != null) {
                intro.hotelIntro_tv.text = introText
            }
        })
    }



}