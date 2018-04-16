package com.seta.whelter.db

import com.seta.whelter.http.GeoBean
import com.seta.whelter.http.ThumbBean
import com.seta.whelter.http.TimelineBean
import com.seta.whelter.http.UserBean

/**
 * Created by SETA_WORK on 2018/4/16.
 *      数据库model类
 */
class Tweet(val map: MutableMap<String, Any?>) {
    var idstr: String by map
    var text: String by map
    var source: String by map
    var favorited: Boolean by map
    var in_reply_to_status_id: String by map
    var in_reply_to_user_id: String by map
    var in_reply_to_screen_name: String by map
    var thumbnail_pic: String? by map
    var bmiddle_pic: String? by map
    var original_pic: String? by map
    var geo: GeoBean? by map
    var user: UserBean? by map
    var retweeted_status: TimelineBean? by map
    var reposts_count: Int by map
    val comments_count: Int by map
    val attitudes_count: Int by map
    var pic_urls: List<ThumbBean> by map
    var gif_ids: String? by map
//                        val visible:??,
//                        val pic_ids:??
}