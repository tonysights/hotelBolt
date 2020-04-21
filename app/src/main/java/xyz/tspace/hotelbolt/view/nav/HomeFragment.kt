package xyz.tspace.hotelbolt.view.nav

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.qmuiteam.qmui.widget.popup.QMUIPopup
import com.qmuiteam.qmui.widget.popup.QMUIPopups
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_home.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.adapter.ImageBannerAdapter
import xyz.tspace.hotelbolt.adapter.TabPageAdapter
import xyz.tspace.hotelbolt.adapter.WaterFallAdapter
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.view.nav.tab.HomeTab_1_Fragment
import xyz.tspace.hotelbolt.viewmodel.MainViewModel

class HomeFragment :
    BaseFragment<MainViewModel>(R.layout.fragment_home, MainViewModel::class) {

    private val mPopupLocationMenu by lazy { makeLocationMenu() }

    override fun setStatusDarkMode(): Boolean? = false

    override fun initView() {
        //抓取网络图片

        setupBanner()
        setupTabUnderBanner()
        setupWaterFall()

    }

    override fun initListener() {
        popupLocation.setOnClickListener { mPopupLocationMenu.show(it) }


        refreshLayout.apply {
            setOnRefreshListener {

            }
            setOnLoadMoreListener {

            }
        }

    }

    override fun initObserver() {
        //新图片获取后，重新加载广告轮播
        viewModel.homeBannerLiveData.observe(this, Observer {
            banner.adapter.setDatas(it)
        })

    }

    //初始化广告轮播
    private fun setupBanner() {
        banner.run {
            viewModel.fetchBannerData()
            this.adapter = ImageBannerAdapter(viewModel.homeBannerLiveData.value)
            indicator = CircleIndicator(requireContext())
        }
    }

    //初始化广告下方TabPager
    private fun setupTabUnderBanner() {
        homeTabPager.let {
            it.adapter = TabPageAdapter(this, HomeTab_1_Fragment(), HomeTab_1_Fragment())
            it.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            TabLayoutMediator(
                homeTab,
                it,
                true,
                TabLayoutMediator.TabConfigurationStrategy { tab, _ ->
                    tab.text = ""
                }).attach()
        }
    }

    //初始化瀑布流
    private fun setupWaterFall() {
        recyclerView.run {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = WaterFallAdapter()
        }
    }

    //初始化选择地址菜单
    private fun makeLocationMenu(): QMUIPopup {
        val cityList = resources.getStringArray(R.array.Zhejiang_city).asList()
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_list_item, cityList)
        val normalPopup = QMUIPopups.listPopup(
            requireContext(),
            dp2px(130), dp2px(300),
            adapter
        ) { _: AdapterView<*>, _: View, i: Int, _: Long ->
            Toast.makeText(requireContext(), "item" + i + 1, Toast.LENGTH_SHORT).show()
            mPopupLocationMenu.dismiss()
        }
            .animStyle(QMUIPopup.ANIM_GROW_FROM_CENTER)
            .preferredDirection(QMUIPopup.DIRECTION_TOP)
            .shadow(true)
            .offsetYIfTop(dp2px(5))
            .onDismiss { Toast.makeText(getContext(), "onDismiss", Toast.LENGTH_SHORT).show() }
        return normalPopup
    }

}