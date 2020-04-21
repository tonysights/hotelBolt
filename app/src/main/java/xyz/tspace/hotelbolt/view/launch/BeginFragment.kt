package xyz.tspace.hotelbolt.view.launch

import kotlinx.android.synthetic.main.fragment_begin.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.viewmodel.LaunchViewModel

class BeginFragment :
    BaseFragment<LaunchViewModel>(R.layout.fragment_begin, LaunchViewModel::class) {

    override fun setStatusDarkMode(): Boolean? = false


    override fun initView() {

    }

    override fun initListener() {
        useAccountLogin_bt.setOnClickListener { navigateTo(R.id.action_beginFragment_to_loginFragment) }
        click_to_register_tv.setOnClickListener { navigateTo(R.id.action_beginFragment_to_registerFragment) }

    }


    override fun initObserver() {

    }


}