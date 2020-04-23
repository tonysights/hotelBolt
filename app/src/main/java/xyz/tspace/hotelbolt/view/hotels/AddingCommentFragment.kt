package xyz.tspace.hotelbolt.view.hotels

import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.fragment_adding_comment.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.adapter.TabPageAdapter
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.view.hotels.tab.ModifyCommentFragment
import xyz.tspace.hotelbolt.viewmodel.MainViewModel

class AddingCommentFragment :
    BaseFragment<MainViewModel>(R.layout.fragment_adding_comment, MainViewModel::class) {
    override fun setStatusDarkMode(): Boolean? = false

    private val args: AddingCommentFragmentArgs by navArgs()
    private val modifyPage = ModifyCommentFragment()
    private val photoPage = ModifyCommentFragment()

    override fun initView() {
        setupViewPager()
    }

    override fun initListener() {
        submitComment_btn.onClick { popBack() }
        back_btn.onClick { popBack() }

    }

    override fun initObserver() {

    }

    private fun setupViewPager() {
        val strArr = getStringArray(R.array.tabTitle_modifyComment)
        viewPager2.let {
            it.adapter = TabPageAdapter(this, modifyPage, photoPage)
            it.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            TabLayoutMediator(
                tabLayout,
                it,
                true,
                TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                    tab.text = strArr[position]
                }).attach()
        }
    }
}