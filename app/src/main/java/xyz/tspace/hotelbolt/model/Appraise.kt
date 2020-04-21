package xyz.tspace.hotelbolt.model

/**
 * 评论对象
 * @param content 评论内容
 * @param createTime 创建时间
 * @param facilitiesGrade 设施评分
 * @param hotelId 酒店id
 * @param hygieneGrade 卫生品分
 * @param id
 * @param liveTime 用户入住时间
 * @param locationGrade 位置评分
 * @param payNumber 订单号
 * @param roomtypeId 房间类型id
 * @param roomtypeName 房间类型名称
 * @param serviceGrade 服务评分
 * @param urlList 评论图片集
 * @param userId 评论用户
 * @param username 评论用户名称
 */
data class Appraise(
    val content: String,
    val createTime: String,
    val facilitiesGrade: Float,
    val hotelId: String,
    val hygieneGrade: Float,
    val id: Int,
    val liveTime: String,
    val locationGrade: Float,
    val payNumber: String,
    val roomtypeId: Int,
    val roomtypeName: String,
    val serviceGrade: Float,
    val urlList: List<String>?,
    val userId: Int,
    val username: String
)

/**
 * TReplyEntity对象
 * @param content 回复内容
 * @param createTime 回复时间
 * @param deleted
 * @param hotelId 酒店id
 * @param toId 回复的用户id
 * @param userId 用户id
 * @param username 用户名
 * */

data class Reply(
    val content: String,
    val createTime: String,
    val deleted: Int,
    val hotelId: Int,
    val id: Int,
    val toId: Int,
    val username: String
)

/**
 * TDiscussEntity对象
 * @param content 评论内容
 * @param createTime 创建时间
 * @param hotelId 酒店id
 * @param id
 * @param replyEntities 回复消息
 * @param userId 用户id
 */
data class Discuss(
    val content: String,
    val createTime: String,
    val hotelId: Int,
    val id: Int,
    val replyEntities: List<Reply>?,
    val userId: Int,
    val username: String

)