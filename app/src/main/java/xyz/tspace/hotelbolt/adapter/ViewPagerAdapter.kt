package xyz.tspace.hotelbolt.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.viewmodel.MainViewModel

class ViewPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {

    private var fragments = emptyArray<BaseFragment>()

    constructor(fm: FragmentManager, fragmentList: Array<BaseFragment>)
            : this(fm, behavior = BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        this.fragments = fragmentList
    }

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size
}