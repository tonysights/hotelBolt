package xyz.tspace.hotelbolt.model

import java.util.*

/**
 * Token对象
 */
data class Token(val token: String?) {
    var createdDate: Date = Date()
    var verifyInfo: Int = 0
    val akt get() = "Bearer $token"
    fun verify() {
        createdDate = Date()
    }

    //验证令牌是否过期,条件包括令牌字符串长度和过期时间
    val isValid: Boolean
        get() {
            if (token != null) {
                val expiredDate = Date(createdDate.time + 60 * 60 * 1000)
                return token.length > 100 && Date().before(expiredDate)
            }
            return false
        }

    constructor(token: String?, mCreatedDate: Date) : this(token) {
        this.createdDate = mCreatedDate
    }
}

data class User(var logincode: String, var password: String) {

    var username: String? = null
    var avatar: String? = null
    var corpCode: String? = null
    var corpName: String? = null
    var createBy: String? = null
    var createDate: String? = null
    var email: String? = null
    var freezeCause: String? = null
    var freezeDate: String? = null
    var hotelId: String? = null
    var id: String? = null
    var lastLoginDate: String? = null
    var lastLoginIp: String? = null
    var mgrType: String? = null
    var mobile: String? = null
    var mobileImei: String? = null
    var phone: String? = null
    var payPassword: String? = null
    var pwdQuestUpdateDate: String? = null
    var pwdQuestion: String? = null
    var pwdQuestion2: String? = null
    var pwdQuestion3: String? = null
    var pwdQuestionAnswer: String? = null
    var pwdQuestionAnswer2: String? = null
    var pwdQuestionAnswer3: String? = null
    var pwdSecurityLevel: Int? = null
    var pwdUpdateDate: String? = null
    var pwdUpdateRecord: String? = null
    var refCode: String? = null
    var refName: String? = null
    var regId: String? = null
    var remarks: String? = null
    var sex: String? = null
    var sign: String? = null
    var status: String? = null
    var updateBy: String? = null
    var updateDate: String? = null
    var userCount: String? = null
    var userLc: String? = null
    var userLevel: String? = null
    var userResetmoney: String? = null
    var userType: String? = null
    var userWeight: String? = null
    var wxOpenid: String? = null
}

