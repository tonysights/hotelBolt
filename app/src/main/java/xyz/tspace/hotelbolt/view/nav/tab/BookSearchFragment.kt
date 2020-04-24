package xyz.tspace.hotelbolt.view.nav.tab

import android.view.View
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_book_search.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.view.calendar.strMonthWithDay

class BookSearchFragment :
    BaseFragment(R.layout.fragment_book_search) {

    override fun initView() {


    }

    override fun initListener() {
        View.OnClickListener {
            navigateTo(R.id.action_navFragment_to_rangeCalendarFragment)
        }.let {
            in_date_tv.setOnClickListener(it)
            out_date_tv.setOnClickListener(it)
        }

    }

    override fun initObserver() {
        mainViewModel.rangeCalendarSaves.observe(this, Observer {
            in_date_tv.text = strMonthWithDay(it[0].month, it[0].day)
            out_date_tv.text = strMonthWithDay(it[1].month, it[1].day)
        })


    }
}