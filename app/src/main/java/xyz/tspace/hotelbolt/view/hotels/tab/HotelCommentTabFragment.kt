package xyz.tspace.hotelbolt.view.hotels.tab

import android.widget.RatingBar
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.vp_hotel_comment.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.adapter.CommentListAdapter
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.view.hotels.HotelDetailFragmentDirections
import xyz.tspace.hotelbolt.viewmodel.HotelViewModel

class HotelCommentTabFragment : BaseFragment(R.layout.vp_hotel_comment) {
    private val hotelViewModel by activityViewModels<HotelViewModel>()

    override fun initView() {
        setupCommentList()
    }

    override fun initListener() {
        ratingBar.setOnRatingBarChangeListener { ratingBar: RatingBar, fl: Float, b: Boolean ->
            val action =
                HotelDetailFragmentDirections.actionHotelDetailFragmentToAddingCommentFragment()
            action.userAppraise = fl
            hotelViewModel.userPraiseFlLive.value = fl
            navigateTo(action)
        }

    }

    override fun initObserver() {
        hotelViewModel

    }

    //评论列表
    private fun setupCommentList() {
        recyclerView.apply {
            adapter = CommentListAdapter()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        }
    }
}