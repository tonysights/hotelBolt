package xyz.tspace.hotelbolt.view.hotels.tab

import android.widget.RatingBar
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.vp_modify_comment.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.viewmodel.MainViewModel

class ModifyCommentFragment :
    BaseFragment<MainViewModel>(R.layout.vp_modify_comment, MainViewModel::class) {
    override fun setStatusDarkMode(): Boolean? = null


    override fun initView() {


    }

    override fun initListener() {
        ratingBar.setOnRatingBarChangeListener { ratingBar: RatingBar, fl: Float, b: Boolean ->
            viewModel.userPraiseFlLive.value = fl
        }

    }

    override fun initObserver() {
        viewModel.userPraiseFlLive.observe(this, Observer {
            ratingBar.rating = it
        })

    }

}