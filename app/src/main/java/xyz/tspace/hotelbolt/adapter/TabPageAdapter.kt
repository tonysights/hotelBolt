package xyz.tspace.hotelbolt.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Booking页面三个Tab选项卡页的适配器，仅适用于ViewPager2
 *
 * @author Wang Rongjie
 */

class TabPageAdapter(container: Fragment, vararg fragments: Fragment) :
    FragmentStateAdapter(container) {

    private var _pageNum: Int = fragments.size
    val pageNum get() = _pageNum
    private val fragmentList = fragments

    override fun getItemCount(): Int = pageNum
    override fun createFragment(position: Int): Fragment = fragmentList[position]


}