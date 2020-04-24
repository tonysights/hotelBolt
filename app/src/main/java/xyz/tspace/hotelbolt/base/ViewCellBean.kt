package xyz.tspace.hotelbolt.base

data class ViewCellBean(
    val title: String,
    val subTitle: String?
) {
    private var _detail: String? = null
    val detail get() = _detail

    private var _imgUrl: String? = null
    val imgUrl get() = _imgUrl

    constructor(
        title: String,
        subTitle: String?,
        detail: String, imgUrl: String
    ) : this(title, subTitle) {
        _detail = detail
        _imgUrl = imgUrl
    }
}

