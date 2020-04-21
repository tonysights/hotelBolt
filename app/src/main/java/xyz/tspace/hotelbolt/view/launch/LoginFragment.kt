package xyz.tspace.hotelbolt.view.launch

import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_login.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.util.TipDialogUtil
import xyz.tspace.hotelbolt.viewmodel.LaunchViewModel

class LoginFragment :
    BaseFragment<LaunchViewModel>(R.layout.fragment_login, LaunchViewModel::class) {

    override fun setStatusDarkMode(): Boolean? = false


    override fun initView() {

    }

    override fun initListener() {
//        login_qmbtn.setOnClickListener { navigateTo(R.id.action_loginFragment_to_navFragment) }
        //注册按钮监听
        go_register_qmbtn.setOnClickListener { navigateTo(R.id.action_loginFragment_to_registerFragment) }
        //登陆按钮监听
        login_qmbtn.setOnClickListener {
            viewModel.login(account_et.text.toString(), reg_pw_et.text.toString())
            TipDialogUtil.show(
                getString(R.string.text_loading),
                TipDialogUtil.LOADING,
                requireContext()
            )
        }

    }

    override fun initObserver() {

        //观察令牌的获取
        viewModel.tokenLive.observe(this, Observer {
            TipDialogUtil.dismiss()
            when (it?.verifyInfo) {
                getInteger(R.integer.LOGIN_SUCCESS) -> {
                    TipDialogUtil.showForTime(
                        getString(R.string.text_loginSuccess),
                        TipDialogUtil.SUCCESS,
                        1000,
                        requireContext()
                    )
                    navigateTo(R.id.action_loginFragment_to_navFragment)
                }
                getInteger(R.integer.LOGIN_FAIL) -> TipDialogUtil.showForTime(
                    getString(R.string.text_wrongPassword),
                    TipDialogUtil.FAIL,
                    1000,
                    requireContext()
                )
            }
        })


    }
}