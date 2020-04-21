package xyz.tspace.hotelbolt.view.launch

import android.os.CountDownTimer
import kotlinx.android.synthetic.main.fragment_splash.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.viewmodel.LaunchViewModel

class SplashFragment :
    BaseFragment<LaunchViewModel>(R.layout.fragment_splash, LaunchViewModel::class) {

    //下一页的地址
    private val nextPage by lazy {
        if (viewModel.canPassLogin) R.id.action_splashFragment_to_navFragment else R.id.action_splashFragment_to_beginFragment
    }

    private val countDown = object : CountDownTimer(3_000, 1_000) {
        override fun onFinish() {
            navigateTo(nextPage)
        }

        override fun onTick(millisUntilFinished: Long) {
            val str =
                (kotlin.math.ceil((millisUntilFinished / 1000).toDouble()).toInt() + 1)
                    .toString() + " " + getString(R.string.text_enter)
            btnLogin.text = str
        }
    }


    override fun setStatusDarkMode(): Boolean? = false
    override fun initView() {
        countDown.start()


    }

    override fun initListener() {
        btnLogin.setOnClickListener {
            navigateTo(nextPage).also { countDown.cancel() }
        }
    }


    override fun initObserver() {

    }


}