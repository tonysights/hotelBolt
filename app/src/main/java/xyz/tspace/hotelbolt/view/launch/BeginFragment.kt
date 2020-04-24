package xyz.tspace.hotelbolt.view.launch

import kotlinx.android.synthetic.main.fragment_begin.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseFragment

class BeginFragment : BaseFragment(R.layout.fragment_begin, false) {


    override fun initView() {

    }

    override fun initListener() {
        useAccountLogin_bt.setOnClickListener { navigateTo(R.id.action_beginFragment_to_loginFragment) }
        click_to_register_tv.setOnClickListener { navigateTo(R.id.action_beginFragment_to_registerFragment) }

    }


    override fun initObserver() {

    }


}