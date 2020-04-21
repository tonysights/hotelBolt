package xyz.tspace.hotelbolt.view.launch

import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.viewmodel.LaunchViewModel

class RegisterFragment :
    BaseFragment<LaunchViewModel>(R.layout.fragment_register, LaunchViewModel::class) {
    override fun setStatusDarkMode(): Boolean? = false
    override fun initView() {

    }

    override fun initListener() {

    }

    override fun initObserver() {

    }
}