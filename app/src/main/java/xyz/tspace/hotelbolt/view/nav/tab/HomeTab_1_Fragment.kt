package xyz.tspace.hotelbolt.view.nav.tab

import kotlinx.android.synthetic.main.tab_first_item_home.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.viewmodel.MainViewModel

class HomeTab_1_Fragment :
    BaseFragment<MainViewModel>(R.layout.tab_first_item_home, MainViewModel::class) {
    override fun setStatusDarkMode(): Boolean? = false
    override fun initView() {

    }

    override fun initListener() {
        selection.setOnClickListener { navigateTo(R.id.action_navFragment_to_hotelListFragment) }

    }

    override fun initObserver() {

    }

}