package xyz.tspace.hotelbolt.view.hotels.tab

import android.widget.RatingBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.vp_hotel_comment.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.adapter.CommentListAdapter
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.view.hotels.HotelDetailFragmentDirections
import xyz.tspace.hotelbolt.viewmodel.MainViewModel

class HotelCommentTabFragment :
    BaseFragment<MainViewModel>(R.layout.vp_hotel_comment, MainViewModel::class) {
    override fun setStatusDarkMode(): Boolean? = false

    override fun initView() {
        setupCommentList()
    }

    override fun initListener() {
        ratingBar.setOnRatingBarChangeListener { ratingBar: RatingBar, fl: Float, b: Boolean ->

            val action =
                HotelDetailFragmentDirections.actionHotelDetailFragmentToAddingCommentFragment()
            action.userAppraise = fl
            viewModel.userPraiseFlLive.value = fl
            navigateTo(action)
        }

    }

    override fun initObserver() {
        viewModel.roomCommentsLive.observe(this, Observer {
            (recyclerView.adapter as CommentListAdapter).submitList(it)
        })

        ratingBar.setOnRatingBarChangeListener { _: RatingBar, fl: Float, _: Boolean ->
            viewModel.userPraiseFlLive.value = fl
        }

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