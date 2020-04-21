package xyz.tspace.hotelbolt.model

data class SearchPay(
    val page: Int
) {
    val importance: String? = null
    val limit: Int? = null
    val sort: String? = null
    val title: String? = null
    val type: String? = null

}


/**
 * 支付对象
 * @param arriveTime 到达时间
 * @param createTime 订单创建时间
 * @param hotelId 酒店id
 * @param id
 * @param jpuShed 是否推送消息
 * @param liveEnd 居住结束日期
 * @param liveStart 居住起始日期
 * @param livedStatus 入住状态
 * @param liverList 入住身份证
 * @param liversEntities
 * @param pay 支付金额
 * @param payNumber 订单号
 * @param payStatus 订单状况
 * @param roomtypeId 房间类型
 * @param roomtypeName 房间名称
 * @param userId 下单用户id
 */
data class Pays(
    val arriveTime: String,
    val createTime: String,
    val hotelId: Int,
    val id: Int,
    val jpuShed: Int?,
    val liveEnd: String,
    val liveStart: String,
    val livedStatus: Int,
    val liverList: String?,
    val liversEntities: List<User>?,
    val pay: Float,
    val payNumber: String,
    val payStatus: String,
    val roomtypeId: Int,
    val roomtypeName: String,
    val userId: String
)