package xyz.tspace.hotelbolt.view.nav

import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.fragment_profile.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.viewmodel.MainViewModel

class ProfileFragment :
    BaseFragment<MainViewModel>(R.layout.fragment_profile, MainViewModel::class) {
    override fun setStatusDarkMode(): Boolean? = true
    override fun initView() {

    }

    override fun initListener() {
        tool_personInfo.onClick { navigateTo(R.id.action_navFragment_to_personalInfoFragment) }

    }

    override fun initObserver() {
        viewModel.userLive.observe(this, Observer {
            Glide.with(this).load(it.avatar).into(qmuiAvatar)
            username_tv.text = it.username
            userTag_1.text = it.userLevel
        })

    }
}