package xyz.tspace.hotelbolt.view.hotels.tab

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton
import kotlinx.android.synthetic.main.vp_room_type_choose.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.adapter.RoomTypeListAdapter
import xyz.tspace.hotelbolt.adapter.TagAdapter
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.model.RoomType
import xyz.tspace.hotelbolt.viewmodel.HotelViewModel

class RoomTypeTabFragment : BaseFragment(R.layout.vp_room_type_choose) {


    private val hotelViewModel by activityViewModels<HotelViewModel>()

    private val clickTagListener = View.OnClickListener { view ->
        if (view is QMUIRoundButton) {
            hotelViewModel.currentActiveTag.let {
                val temp = it.value
                it.value = if (temp == getString(R.string.status_not_activated)) {
                    view.apply {
                        setTextColor(getColor(R.color.qmui_config_color_white))
                        setBackgroundColor(getColor(R.color.qmui_config_color_50_blue))
                    }
                    view.text.toString()
                } else {
                    view.apply {
                        setTextColor(getColor(R.color.qmui_config_color_gray_1))
                        setBackgroundColor(getColor(R.color.qmui_config_color_gray_9))
                    }
                    getString(R.string.status_not_activated)
                }

            }
        }
    }


    override fun initView() {

        //初始化房间类型列表
        roomTypeRv.run {
            adapter = RoomTypeListAdapter()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        roomTypeTagRv.run {
            adapter = TagAdapter(clickTagListener)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

    }

    override fun initListener() {

    }

    override fun initObserver() {
        hotelViewModel.roomTypeListLive.observe(this, Observer {
            if (it.isNotEmpty()) {
                (roomTypeRv.adapter as RoomTypeListAdapter).submitList(it)

                val rtIterator = it.iterator()
                val tagSet = mutableSetOf<String>()
                while (rtIterator.hasNext()) {
                    tagSet.add(rtIterator.next().roomType.toString())
                }
                val tagList = tagSet.toList()
                (roomTypeTagRv.adapter as TagAdapter).submitList(tagList)

            }
        })
        hotelViewModel.currentActiveTag.observe(this, Observer {
            val selectedRoomTypes = mutableListOf<RoomType>()
            val iterator = hotelViewModel.roomTypeListLive.value?.iterator()
            if (it != getString(R.string.status_not_activated) && iterator != null) {
                while (iterator.hasNext()) {
                    val temp = iterator.next()
                    if (temp.roomType == it) {
                        selectedRoomTypes.add(temp)
                    }
                }
                (roomTypeRv.adapter as RoomTypeListAdapter).submitList(selectedRoomTypes)
            } else {
                (roomTypeRv.adapter as RoomTypeListAdapter).submitList(hotelViewModel.roomTypeListLive.value)
            }
        })


    }
}