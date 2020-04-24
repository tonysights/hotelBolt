package xyz.tspace.hotelbolt.view.hotels

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_hotel.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.adapter.HotelListAdapter
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.util.TipDialogUtil
import xyz.tspace.hotelbolt.viewmodel.HotelViewModel

class HotelListFragment : BaseFragment(R.layout.fragment_list_hotel, false) {

    private val hotelViewModel by activityViewModels<HotelViewModel>()

    override fun initView() {
        setupHotelList()

    }

    override fun initListener() {
        toolbar.setNavigationOnClickListener { popBack() }

    }

    override fun initObserver() {
        hotelViewModel.hotelListLive.observe(this, Observer {
            (hotelList_rv.adapter as HotelListAdapter).submitList(it)
            TipDialogUtil.dismiss()
        })


    }

    private fun setupHotelList() {
        updateListInfo()
        val hotelListAdapter = HotelListAdapter()
        hotelList_rv.apply {
            adapter = hotelListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun updateListInfo() {
        hotelViewModel.fetchHotelList()
        TipDialogUtil.show(
            getString(R.string.text_loading),
            TipDialogUtil.LOADING,
            requireContext()
        )

    }

}