package xyz.tspace.hotelbolt.view.identity

import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_set_info.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseFragment

class SetInfoFragment :
    BaseFragment(R.layout.fragment_set_info, false) {


    override fun initView() {

    }

    override fun initListener() {

    }

    override fun initObserver() {
        mainViewModel.userLive.observe(requireActivity(), Observer {
            usernameTextView.setText(it.username)
        })

    }
}