package xyz.tspace.hotelbolt.view.nav

import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction
import kotlinx.android.synthetic.main.fragment_bottom_nav.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.adapter.ViewPagerAdapter
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.viewmodel.MainViewModel

class NavFragment :
    BaseFragment<MainViewModel>(R.layout.fragment_bottom_nav, MainViewModel::class) {

    val darkBackground by lazy {
        ContextCompat.getDrawable(
            requireContext(),
            R.color.colorPrimaryDark
        )
    }
    val lightBackground by lazy {
        ContextCompat.getDrawable(
            requireContext(),
            R.color.default_BookPage_backgroundColor
        )
    }
    private var curMenuItem: MenuItem? = null

    val expiredIdDialog by lazy {
        QMUIDialog.MessageDialogBuilder(requireContext())
            .setMessage(getString(R.string.expiredId))
            .setTitle(getString(R.string.text_expiredIdTitle))
            .addAction(
                R.drawable.ic_error_outline_red_24dp,
                getString(R.string.text_confirm),
                object : QMUIDialogAction.ActionListener {
                    override fun onClick(dialog: QMUIDialog?, index: Int) {
                        dialog?.dismiss()
                            .also { navigateTo(R.id.action_navFragment_to_beginFragment) }

                    }
                })
            .setCanceledOnTouchOutside(false)
            .create()
    }


    override fun setStatusDarkMode(): Boolean? = false
    override fun initView() {
        viewModel.fetchUserInfo()
        setupBottomNavWithFragments()


    }

    override fun initListener() {


    }

    override fun initObserver() {
        //观察token的过期情况
        viewModel.tokenLive.observe(this, Observer {
            //若非初始token
            if (it.token.equals(getString(R.string.token_expired))) expiredIdDialog.show()
        })

    }

    //在viewPager的闭包中对其和底部导航栏进行配置
    private fun setupBottomNavWithFragments() {
        with(viewPager) {
            adapter = ViewPagerAdapter(
                childFragmentManager,
                arrayOf(HomeFragment(), BookFragment(), HistoryFragment(), ProfileFragment())
            )

            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}

                override fun onPageScrolled(
                    position: Int, positionOffset: Float, positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {
                    curMenuItem?.setChecked(false)
                    if (curMenuItem == null) bottom_nav.menu.getItem(0).setChecked(false)
                    curMenuItem = bottom_nav.menu.getItem(position).run { setChecked(true) }
                }
            })
            bottom_nav.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.homeFragment -> this.setCurrentItem(0).also {
                        container.background = lightBackground
                    }
                    R.id.bookFragment -> this.setCurrentItem(1).also {
                        container.background = lightBackground
                    }
                    R.id.historyFragment -> this.setCurrentItem(2).also {
                        container.background = lightBackground
                    }
                    R.id.profileFragment -> this.setCurrentItem(3).also {
                        container.postDelayed({ container.background = darkBackground }, 180)
                    }
                }
                false
            }
        }
    }

    private fun showExpiredIdentification() {


    }
}