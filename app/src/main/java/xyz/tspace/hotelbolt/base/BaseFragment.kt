package xyz.tspace.hotelbolt.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlin.reflect.KClass

/**
 * 封装Fragment的基本使用
 * 对于MVVM模式、单Activity结构App的View(Fragment)，由于已经不再负责业务逻辑，所以代码根据目的可以分为：
 *
 *      # 初始化需要动态加载的视图，或者改变视图的属性
 *      # 观察ViewModel中的数据变化
 *      # 监听用户的操作并执行相应操作
 *
 * 因此，这三种行为对应封装的三个init*()方法
 *
 * @author WRJ
 * 2020-3-23
 */
abstract class BaseFragment<VM : BaseViewModel>(contentLayoutId: Int, viewModelClazz: KClass<VM>) :
    Fragment(contentLayoutId) {

    val viewModel by lazy { ViewModelProvider(requireActivity()).get(viewModelClazz.java) }

    val TAG = this.javaClass.name

    val layoutResId = contentLayoutId

    private val navController by lazy { view?.findNavController() }

    val inflater: LayoutInflater get() = LayoutInflater.from(requireContext())

    val layoutParent get() = view?.rootView as ViewGroup

    fun getColor(resId: Int) = ContextCompat.getColor(requireContext(), resId)

    fun getInteger(resId: Int) = resources.getInteger(resId)

    fun getStringArray(resId: Int) = resources.getStringArray(resId)


    fun navigateTo(actionId: Int) {
        navController?.navigate(actionId)
    }

    fun navigateTo(actionId: Int, args: Bundle) {
        navController?.navigate(actionId, args)
    }

    fun navigateTo(navDirections: NavDirections) {
        navController?.navigate(navDirections)
    }

    fun popBack() {
        navController?.popBackStack()
    }


    abstract fun setStatusDarkMode(): Boolean?

    //初始化视图
    abstract fun initView()

    //初始化监听器
    abstract fun initListener()

    //初始化观察器
    abstract fun initObserver()

    fun dp2px(dp: Int) = QMUIDisplayHelper.dp2px(requireContext(), dp)

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //配置状态栏透明和字体颜色
        QMUIStatusBarHelper.translucent(requireActivity())
        requireActivity().apply {
            QMUIStatusBarHelper.translucent(this)
            val temp = setStatusDarkMode()
            if (temp != null) {
                if (temp)
                    QMUIStatusBarHelper.setStatusBarDarkMode(this)
                else QMUIStatusBarHelper.setStatusBarLightMode(this)
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    final override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

}


