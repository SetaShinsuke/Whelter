package com.seta.whelter.http

/**
 * Created by SETA_WORK on 2018/3/19.
 */
data class TimelinePojo(val statuses: List<TimelineBean>)

data class TimelineBean(val id: Int, val mid: Int, val idstr: String, val text: String, val source: String, val favorited: Boolean,
                        val in_reply_to_status_id: String, val in_reply_to_user_id: String, val in_reply_to_screen_name: String,
                        val thumbnail_pic: String? = null,
                        val bmiddle_pic: String? = null,
                        val original_pic: String? = null,
                        val geo: GeoBean? = null,
                        val user: UserBean? = null,
                        val retweeted_status: TimelineBean? = null,
                        val reposts_count: Int, val comments_count: Int, val attitudes_count: Int,
                        val pic_urls: List<thumbBean> = listOf(),
                        val gif_ids: String? = null
//                        val visible:??,
//                        val pic_ids:??
)

data class thumbBean(val thumbnail_pic: String)

//地理信息
data class GeoBean(val province: String)

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
                    val gender: String,
                    val followers_count: String,
                    val friends_count: Int,
                    val statuses_count: Int,
                    val favourites_count: Int,
                    val created_at: Int,
                    val following: Boolean,
                    val avatar_large: String,
                    val avatar_hd: String,
                    val bi_followers_count: Int //互粉数
)