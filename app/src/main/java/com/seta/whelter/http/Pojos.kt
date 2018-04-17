package com.seta.whelter.http

/**
 * Created by SETA_WORK on 2018/3/19.
 */
data class TimelinePojo(val statuses: List<TimelineBean>)

data class TimelineBean(val id: String,
                        val mid: String,
                        val idstr: String,
                        val text: String,
                        val source: String,
                        val favorited: Boolean,
                        val in_reply_to_status_id: String,
                        val in_reply_to_user_id: String,
                        val in_reply_to_screen_name: String,
                        val thumbnail_pic: String? = null,
                        val bmiddle_pic: String? = null,
                        val original_pic: String? = null,
                        val geo: GeoBean? = null,
                        val user: UserBean? = null,
                        val retweeted_status: TimelineBean? = null,
                        val reposts_count: Int, val comments_count: Int, val attitudes_count: Int,
                        val pic_urls: List<ThumbBean> = listOf(),
                        val gif_ids: String? = null
//                        val visible:??,
//                        val pic_ids:??
)

data class ThumbBean(val thumbnail_pic: String)

data class UserBean(val id: String,
                    val screen_name: String,
                    val name: String,
                    val province: String,
                    val city: String,
                    val location: String,
                    val description: String,
                    val url: String,
                    val profile_image_url: String,
                    val domain: String,
                    val gender: String, //性别，m：男、f：女、n：未知
                    val followers_count: String,
                    val friends_count: Int,
                    val statuses_count: Int,
                    val favourites_count: Int,
                    val created_at: String,
                    val following: Boolean, //未支持
                    val avatar_large: String,
                    val avatar_hd: String,
                    val bi_followers_count: Int //互粉数
)

data class RateLimitPojo(val ip_limit: Int, val limit_time_unit: String, val remaining_ip_hits: Int, val remaining_user_hits: Int, val reset_time: String, val reset_time_in_seconds: Int, val user_limit: Int, val api_rate_limits: List<ApiLimitBean>)
data class ApiLimitBean(val api: String, val limit: Int, val limit_time_unit: String, val remaining_hits: Int)


//地理信息
//返回值字段	字段类型	字段说明
//longitude	string	经度坐标
//latitude	string	维度坐标
//city	string	所在城市的城市代码
//province	string	所在省份的省份代码
//city_name	string	所在城市的城市名称
//province_name	string	所在省份的省份名称
//address	string	所在的实际地址，可以为空
//pinyin	string	地址的汉语拼音，不是所有情况都会返回该字段
//more	string	更多信息，不是所有情况都会返回该字段
data class GeoBean(
        val longitude: String,
        val latitude: String,
        val province: String,
        val city: String,
        val city_name: String,
        val province_name: String,
        val address: String = "",
        val pinyin: String = "",
        val more: String = ""
)