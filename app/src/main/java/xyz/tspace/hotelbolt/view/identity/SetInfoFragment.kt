package xyz.tspace.hotelbolt.view.identity

import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_set_info.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.viewmodel.MainViewModel

class SetInfoFragment :
    BaseFragment<MainViewModel>(R.layout.fragment_set_info, MainViewModel::class) {
    override fun setStatusDarkMode(): Boolean? = false

    override fun initView() {

    }

    override fun initListener() {

    }

    override fun initObserver() {
        viewModel.userLive.observe(requireActivity(), Observer {
            usernameTextView.setText(it.username)
        })

    }
}