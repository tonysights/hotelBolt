package xyz.tspace.hotelbolt.view.calendar

import android.widget.Toast
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.fragment_range_calendar.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseFragment

class RangeCalendarFragment :
    BaseFragment(R.layout.fragment_range_calendar, false) {
    private val weekArray by lazy { getStringArray(R.array.week_array) }

    private val calendarSaves: Array<Calendar> = arrayOf(Calendar(), Calendar())

    override fun initView() {
        calendarView.let { yearWithMonth.setText(strYearWithMonth(it.curYear, it.curMonth)) }


    }

    override fun initListener() {
        calendarView.apply {

            setOnMonthChangeListener { year, month ->
                yearWithMonth.text = strYearWithMonth(year, month)
            }

            setOnCalendarRangeSelectListener(object : CalendarView.OnCalendarRangeSelectListener {

                override fun onCalendarSelectOutOfRange(calendar: Calendar?) {}

                override fun onCalendarRangeSelect(calendar: Calendar?, isEnd: Boolean) {
                    mainViewModel.rangeCalendarSaves.value ?: arrayOf(Calendar(), Calendar())
                    if (!isEnd) {
                        calendar?.run {
                            tv_left_date.text = strMonthWithDay(month, day)
                            tv_left_week.text = weekArray[week]
                            tv_right_week.text = getString(R.string.text_checkOutDate)
                            tv_right_date.text = ""
                            calendarSaves[0] = this
                        }
                    } else {
                        calendar?.run {
                            tv_right_date.text = strMonthWithDay(month, day)
                            tv_right_week.text = weekArray[week]
                            calendarSaves[1] = this
                        }
                    }
                }

                override fun onSelectOutOfRange(calendar: Calendar?, isOutOfMinRange: Boolean) {
                    Toast.makeText(
                        requireContext(),
                        calendar.toString() + if (isOutOfMinRange) "小于最小选择范围" else "超过最大选择范围",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

            setOnCalendarInterceptListener(object : CalendarView.OnCalendarInterceptListener {
                override fun onCalendarInterceptClick(calendar: Calendar?, isClick: Boolean) {
                    Toast.makeText(
                        requireContext(),
                        calendar.toString() + if (isClick) "拦截不可点击" else "拦截设定为无效日期",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onCalendarIntercept(calendar: Calendar?): Boolean = false
            })
        }
        tv_commit.onClick {
            mainViewModel.rangeCalendarSaves.value = calendarSaves
            popBack()
        }
        iv_clear.onClick { popBack() }

    }

    override fun initObserver() {

    }
}

fun strMonthWithDay(month: Int, day: Int) = "${month}月 ${day}日"
fun strYearWithMonth(year: Int, month: Int) = "${year}年 ${month}月"