package xyz.tspace.hotelbolt.model

class DataProvider {
    companion object {

        fun getPreviewImgList(): List<PixabayImage> {

            val strs = listOf(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586694576457&di=4fc0ecfbe20ecc621b1d02c3d606e849&imgtype=0&src=http%3A%2F%2Fwww.qlfz365.cn%2Fjinan%2FUploadFiles_7794%2F202003%2F20200319173341724.jpeg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586694576457&di=39b9960c6b61baf250787abe9fdba47f&imgtype=0&src=http%3A%2F%2Fgb.cri.cn%2Fmmsource%2Fimages%2F2014%2F04%2F13%2F90%2F5623406131448295614.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586694576456&di=3476ef62a99f2a6bdefba3fcac8a7375&imgtype=0&src=http%3A%2F%2Fdimg04.c-ctrip.com%2Fimages%2F300c0a0000004mjmaB009.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586694576456&di=ccf9ab0bffa2ec6ed8cb0da764b038cc&imgtype=0&src=http%3A%2F%2Fpavo.elongstatic.com%2Fi%2FHotel870_470%2Fnw_000dLDu2.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586694576456&di=e1efa0fcacb4691c55a57374401fd95e&imgtype=0&src=http%3A%2F%2Fimages4.c-ctrip.com%2Ftarget%2Ftuangou%2F673%2F058%2F213%2Fd14e4aaabe844ebd951a3d75ba55f2bf_720_480.jpg"
            )
            val list = mutableListOf<PixabayImage>()
            val iterator = strs.listIterator()
            while (iterator.hasNext()) {
                list.add(PixabayImage("default", iterator.next()))
            }
            return list;
        }

        fun getInitUser(): User {
            return User("0", "0").apply {
                avatar = "https://avatar-1300111898.cos.ap-shanghai.myqcloud.com/MyProfile.jpg"
                email = ""
                phone = ""
                username = "游客~"
                wxOpenid = ""
                sign = "这个人很懒，一句话都没有说~"
            }
        }

        fun getInitHotelRoomList(): List<HotelRoom> {

            return ArrayList<HotelRoom>().apply {
                add(HotelRoom("0"))
            }
        }
    }
}