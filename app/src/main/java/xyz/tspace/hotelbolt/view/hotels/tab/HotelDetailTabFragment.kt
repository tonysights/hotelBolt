package xyz.tspace.hotelbolt.view.hotels.tab

import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.include_hotel_info.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.viewmodel.MainViewModel

class HotelDetailTabFragment :
    BaseFragment<MainViewModel>(R.layout.vp_hotel_detail, MainViewModel::class) {
    override fun setStatusDarkMode(): Boolean? = null
    override fun initView() {

    }

    override fun initListener() {

    }

    override fun initObserver() {

        viewModel.hotelRoomLive.observe(requireActivity(), Observer {
            this.hotelIntro_tv.text = it[0].roomDetails
        })

    }
}