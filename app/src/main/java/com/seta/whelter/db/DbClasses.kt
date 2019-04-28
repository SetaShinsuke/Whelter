package com.seta.whelter.db

import java.util.*

/**
 * Created by SETA_WORK on 2018/4/16.
 *      数据库model类
 */
class TweetDb(val map: MutableMap<String, Any?>, val fromTweet: TweetDb? = null, val geo: GeoDb? = null) {
    var uid: String by map
    var content: String by map
    var source: String by map
    var favorited: Boolean by map
    var thumbPicUrl: String? by map
    var middlePicUrl: String? by map
    var originalPicUrl: String? by map
    var repostsCount: Int by map
    val commentsCount: Int by map
    val attitudesCount: Int by map

    constructor(uid: String, content: String, source: String, favorited: Boolean, thumbPicUrl: String? = null, middlePicUrl: String? = null, originalPicUrl: String? = null, repostsCount: Int, commentsCount: Int, attitudesCount: Int) : this(HashMap()) {
        this.uid = uid
    }
}

class UserDb(val map: MutableMap<String, Any?>) {
    var id: String by map
    var screen_name: String by map
    var name: String by map
    var province: String by map
    var city: String by map
    var location: String by map
    var description: String by map
    var url: String by map
    var profile_image_url: String by map
    var domain: String by map
    var gender: String by map
    var followers_count: String by map
    var friends_count: Int by map
    var statuses_count: Int by map
    var favourites_count: Int by map
    var created_at: String by map
    var following: Boolean by map
    var avatar_large: String by map
    var avatar_hd: String by map
    var bi_followers_count: Int by map
}

//坐标
class GeoDb(val map: MutableMap<String, Any?>) {
    var longitude: String by map
    var latitude: String by map
    var province: String by map
    var city: String by map
    var city_name: String by map
    var province_name: String by map
    var address: String by map.withDefault { "" }
    var pinyin: String by map.withDefault { "" }
    var more: String by map.withDefault { "" }
}