package xyz.tspace.hotelbolt.view.launch

import android.graphics.PointF
import android.os.CountDownTimer
import androidx.fragment.app.viewModels
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import kotlinx.android.synthetic.main.fragment_splash.*
import xyz.tspace.animview.bean.CircleBean
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.viewmodel.LaunchViewModel

class SplashFragment : BaseFragment(R.layout.fragment_splash, false) {

    private val launchViewModel: LaunchViewModel by viewModels()

    private val circleBeanList: MutableList<CircleBean> = mutableListOf()

    //下一页的地址
    private val nextPage by lazy {
        if (launchViewModel.canPassLogin) R.id.action_splashFragment_to_navFragment else R.id.action_splashFragment_to_beginFragment
    }

    //倒计时
    private val countDown = object : CountDownTimer(4_000, 1_000) {
        override fun onFinish() {
            bubbleView.stopAnimation()
            navigateTo(nextPage)
        }

        override fun onTick(millisUntilFinished: Long) {
            val str =
                (kotlin.math.ceil((millisUntilFinished / 1000).toDouble())).toInt()
                    .toString() + " " + getString(R.string.text_enter)
            btnLogin.text = str
        }
    }

    override fun initView() {
        bubbleView.run {
            initPoint()
            circleBeen = circleBeanList
            centerImg = logoImage
            openAnimation()
        }
        countDown.start()
    }

    override fun initListener() {
        btnLogin.setOnClickListener {
            bubbleView.stopAnimation()
            navigateTo(nextPage).also { countDown.cancel() }
        }
    }

    override fun initObserver() {

    }

    private fun initPoint() {
        val height: Int = QMUIDisplayHelper.getScreenHeight(requireContext())
        val width: Int = QMUIDisplayHelper.getScreenWidth(requireContext())
        val centerX = width / 2
        val centerY = height / 2

        val cb1 = CircleBean(
            PointF((-width / 5.1).toFloat(), (height / 1.5).toFloat()),
            PointF((centerX - 30).toFloat(), (height * 2 / 3).toFloat()),
            PointF((width / 2.4).toFloat(), (height / 3.4).toFloat()),
            PointF((width / 6).toFloat(), (centerY - 120).toFloat()),
            PointF((width / 7.2).toFloat(), (-height / 128).toFloat()),
            (width / 14.4).toFloat(), 60
        )

        val cb2 = CircleBean(
            PointF((-width / 4).toFloat(), (height / 1.3).toFloat()),
            PointF((centerX - 20).toFloat(), (height * 3 / 5).toFloat()),
            PointF((width / 2.1).toFloat(), (height / 2.5).toFloat()),
            PointF((width / 3).toFloat(), (centerY - 10).toFloat()),
            PointF((width / 4).toFloat(), (-height / 5.3).toFloat()),
            (width / 4).toFloat(), 60
        )
        val cb3 = CircleBean(
            PointF((-width / 12).toFloat(), (height / 1.1).toFloat()),
            PointF((centerX - 100).toFloat(), (height * 2 / 3).toFloat()),
            PointF((width / 3.4).toFloat(), (height / 2).toFloat()),
            PointF(0F, (centerY + 100).toFloat()),
            PointF(0F, 0F),
            (width / 24).toFloat(), 60
        )

        val cb4 = CircleBean(
            PointF((-width / 9).toFloat(), (height / 0.9).toFloat()),
            PointF(centerX.toFloat(), (height * 3 / 4).toFloat()),
            PointF((width / 2.1).toFloat(), (height / 2.3).toFloat()),
            PointF((width / 2).toFloat(), centerY.toFloat()),
            PointF((width / 1.5).toFloat(), (-height / 5.6).toFloat()),
            (width / 4).toFloat(), 60
        )

        val cb5 = CircleBean(
            PointF((width / 1.4).toFloat(), (height / 0.9).toFloat()),
            PointF(centerX.toFloat(), (height * 3 / 4).toFloat()),
            PointF((width / 2).toFloat(), (height / 2.37).toFloat()),
            PointF((width * 10 / 13).toFloat(), (centerY - 20).toFloat()),
            PointF((width / 2).toFloat(), (-height / 7.1).toFloat()),
            (width / 4).toFloat(), 60
        )
        val cb6 = CircleBean(
            PointF((width / 0.8).toFloat(), height.toFloat()),
            PointF((centerX + 20).toFloat(), (height * 2 / 3).toFloat()),
            PointF((width / 1.9).toFloat(), (height / 2.3).toFloat()),
            PointF((width * 11 / 14).toFloat(), (centerY + 10).toFloat()),
            PointF((width / 1.1).toFloat(), (-height / 6.4).toFloat()),
            (width / 4).toFloat(), 60
        )
        val cb7 = CircleBean(
            PointF((width / 0.9).toFloat(), (height / 1.2).toFloat()),
            PointF((centerX + 20).toFloat(), (height * 4 / 7).toFloat()),
            PointF((width / 1.6).toFloat(), (height / 1.9).toFloat()),
            PointF(width.toFloat(), (centerY + 10).toFloat()),
            PointF(width.toFloat(), 0F),
            (width / 9.6).toFloat(), 60
        )
        circleBeanList.addAll(0, listOf(cb1, cb2, cb3, cb4, cb5, cb6, cb7))
    }


}