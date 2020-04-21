package xyz.tspace.hotelbolt.view.identity

import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.qmuiteam.qmui.widget.QMUIRadiusImageView
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout
import kotlinx.android.synthetic.main.fragment_personal_info.*
import xyz.tspace.hotelbolt.R
import xyz.tspace.hotelbolt.base.BaseFragment
import xyz.tspace.hotelbolt.model.User
import xyz.tspace.hotelbolt.viewmodel.MainViewModel

class PersonalInfoFragment :
    BaseFragment<MainViewModel>(R.layout.fragment_personal_info, MainViewModel::class) {
    override fun setStatusDarkMode(): Boolean? = false
    private var loadedUser: User? = null

    //对列表项使用懒加载
    //头像项
    private val avatarItem by lazy { groupListView.createItemView(getString(R.string.text_avatar)) }

    //用户ID项
    private val idItem by lazy { groupListView.createItemView(getString(R.string.text_id)) }

    //用户名项
    private val nicknameItem by lazy { groupListView.createItemView(getString(R.string.text_username)) }

    //性别项
    private val genderItem by lazy { groupListView.createItemView(getString(R.string.text_gender)) }

    // 出生日期项
    private val birthdayItem by lazy { groupListView.createItemView(getString(R.string.text_birthday)) }

    //个性签名项
    private val mottoItem by lazy { groupListView.createItemView(getString(R.string.text_motto)) }

    //邮箱项
    private val emailItem by lazy { groupListView.createItemView(getString(R.string.text_email)) }

    //手机号码项
    private val phoneItem by lazy { groupListView.createItemView(getString(R.string.text_phone)) }

    //微信项
    private val wechatItem by lazy { groupListView.createItemView(getString(R.string.text_wechat)) }

    override fun initView() {
        viewModel.fetchUserInfo()
        setupGroupList()
    }

    override fun initListener() {
        toolbar.setNavigationOnClickListener { popBack() }
        pull_to_refresh.setOnPullListener(object : QMUIPullRefreshLayout.OnPullListener {
            override fun onMoveRefreshView(offset: Int) {}
            override fun onMoveTarget(offset: Int) {}
            override fun onRefresh() {
                updateGroupListItem()
                pull_to_refresh.run { postDelayed({ finishRefresh() }, 1500) }
            }
        })
        //跳转到设置用户名
        nicknameItem.setOnClickListener { navigateTo(R.id.action_personalInfoFragment_to_setInfoFragment) }


    }

    override fun initObserver() {
        viewModel.userLive.observe(requireActivity(), Observer {
            loadedUser = it
            pull_to_refresh.setToRefreshDirectly(1000)
        })

    }

    private fun setupGroupList() {
        updateGroupListItem()
        QMUIGroupListView.newSection(requireContext())
            .setTitle(getString(R.string.text_personalInfo))
            .addItemView(avatarItem, null)
            .addItemView(idItem, null)
            .addItemView(nicknameItem, null)
            .addItemView(genderItem, null)
            .addItemView(birthdayItem, null)
            .addItemView(mottoItem, null)
            .addTo(groupListView)

        QMUIGroupListView.newSection(requireContext())
            .setTitle(getString(R.string.text_contact))
            .addItemView(emailItem, null)
            .addItemView(phoneItem, null)
            .addItemView(wechatItem, null)
            .addTo(groupListView)
    }

    private fun updateGroupListItem() {

        avatarItem.apply {
            accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM
            val imageView = QMUIRadiusImageView(requireContext()).also { it.cornerRadius = 30 }
            Glide.with(this)
                .load("https://avatar-1300111898.cos.ap-shanghai.myqcloud.com/MyProfile.jpg")
                .error(R.drawable.ic_remove_circle_outline_black_24dp)
                .placeholder(R.drawable.ic_person)
                .into(imageView)
            addAccessoryCustomView(imageView)
        }

        idItem.apply {
            accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_NONE
            detailText = loadedUser?.id
        }

        nicknameItem.apply {
            accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
            detailText = loadedUser?.username
        }

        genderItem.apply {
            accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
            detailText = loadedUser?.sex
        }

        birthdayItem.apply {
            accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
            detailText = "1998-12-12"
        }

        mottoItem.apply {
            accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
            detailText = loadedUser?.sign
        }

        emailItem.apply {
            accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
            detailText = loadedUser?.email
        }

        phoneItem.apply {
            accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
            detailText = loadedUser?.phone
        }
        wechatItem.apply {
            accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
            detailText = loadedUser?.wxOpenid
        }
    }
}