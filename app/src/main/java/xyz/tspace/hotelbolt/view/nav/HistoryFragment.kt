package xyz.tspace.hotelbolt.view.nav

import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.viewmodel.MainViewModel

class HistoryFragment :
    BaseFragment<MainViewModel>(R.layout.fragment_history, MainViewModel::class) {
    override fun setStatusDarkMode(): Boolean? = false
    override fun initView() {

    }

    override fun initListener() {

    }

    override fun initObserver() {

    }
}