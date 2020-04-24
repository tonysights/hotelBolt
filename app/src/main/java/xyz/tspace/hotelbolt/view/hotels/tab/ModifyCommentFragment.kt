package xyz.tspace.hotelbolt.view.hotels.tab

import android.widget.RatingBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.vp_modify_comment.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.viewmodel.HotelViewModel

class ModifyCommentFragment : BaseFragment(R.layout.vp_modify_comment) {

    private val hotelViewModel by viewModels<HotelViewModel>()

    override fun initView() {


    }

    override fun initListener() {
        ratingBar.setOnRatingBarChangeListener { ratingBar: RatingBar, fl: Float, b: Boolean ->
            hotelViewModel.userPraiseFlLive.value = fl
        }

    }

    override fun initObserver() {

    }

}