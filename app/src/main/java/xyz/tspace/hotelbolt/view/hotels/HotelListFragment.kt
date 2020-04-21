package xyz.tspace.hotelbolt.view.hotels

import android.text.Layout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_hotel.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.adapter.HotelListAdapter
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.util.TipDialogUtil
import xyz.tspace.hotelbolt.view.nav.NavFragment
import xyz.tspace.hotelbolt.viewmodel.MainViewModel

class HotelListFragment :
    BaseFragment<MainViewModel>(R.layout.fragment_list_hotel, MainViewModel::class) {
    override fun setStatusDarkMode(): Boolean? = false
    override fun initView() {
        setupHotelList()


    }

    override fun initListener() {
        toolbar.setNavigationOnClickListener { popBack() }
    }

    override fun initObserver() {
        viewModel.hotelListLive.observe(this, Observer {
            val hotelAdapter = hotelList_rv.adapter as HotelListAdapter
            hotelAdapter.submitList(it)
            TipDialogUtil.dismiss()


        })

    }

    private fun setupHotelList() {
        val hotelListAdapter = HotelListAdapter()
        hotelList_rv.apply {
            adapter = hotelListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        updateListInfo()
    }

    private fun updateListInfo() {
        viewModel.fetchAllHotelInfo()
        TipDialogUtil.show(
            getString(R.string.text_loading),
            TipDialogUtil.LOADING,
            requireContext()
        )

    }

}