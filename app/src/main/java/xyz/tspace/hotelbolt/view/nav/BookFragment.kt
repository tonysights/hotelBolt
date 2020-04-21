package xyz.tspace.hotelbolt.view.nav

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_book.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.adapter.TabPageAdapter
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.view.nav.tab.BookSearchFragment
import xyz.tspace.hotelbolt.viewmodel.MainViewModel

class BookFragment :
    BaseFragment<MainViewModel>(R.layout.fragment_book, MainViewModel::class) {

    private val searchPage = BookSearchFragment()

    private val historyPage = BookSearchFragment()

    private val favPage = BookSearchFragment()

    override fun setStatusDarkMode(): Boolean? = false


    override fun initView() {
        setupViewPager()

    }

    override fun initListener() {

    }

    override fun initObserver() {

    }

    private fun setupViewPager() {
        val strArr = getStringArray(R.array.book_tab_array)
        bookpage_viewpager2.let {
            it.adapter = TabPageAdapter(this, searchPage, historyPage, favPage)
            it.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            TabLayoutMediator(
                book_tab_layout,
                it,
                true,
                TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                    tab.text = strArr[position]
                }).attach()
        }


    }
}