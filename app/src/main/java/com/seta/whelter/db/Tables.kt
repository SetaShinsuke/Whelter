package com.seta.whelter.db

/**
 * Created by SETA_WORK on 2018/4/16.
 * 数据库的列
 */
object TweetTable {
    val TABLE_NAME = "Tweets"
    val UID = "uid"
    val CONTENT = "content"
    val SOURCE = "source"
    val FAVORITED = "favorited"
    val THUMB_PIC_URL = "thumbPicUrl"
    val MIDDLE_PIC_URL = "middlePicUrl"
    val ORG_PIC_URL = "originalPicUrl"
    val REPOSTS_COUNT = "repostsCount"
    val COMMENTS_COUNT = "commentsCount"
    val ATTITUDES_COUNT = "attitudesCount"
    val RETWEETED_UID = "retweetedUid"
    val GEO_ID = "geoId"
    val USER_ID = "userId"
}

object UserTable {
    val TABLE_NAME = "Users"
    val UID = "uid"
    val SCREEN_NAME = "screenName"
    val NAME = "name"
    val PROVINCE = "province"
    val CITY = "city"
    val LOCATION = "LOCATION"
    val DESCRIPTION = "description"
    val URL = "url"
    val PROFILE_IMG_URL = "profile_img_url"
    val DOMAIN = "domain"
    val GENDER = "gender"
    val FOLLOWERS_COUNT = "followersCount"
    val FRIENDS_COUNT = "friendsCount"
    val TWEETS_COUNT = "tweetsCount"
    val FAVORITES_COUNT = "favoritesCount"
    val REGISTERED_AT = "registeredAt"
    val AVATAR_LARGE = "avatarLarge"
    val AVATAR_HD = "avatarHd"
}

object UserTweetRelationTable {
    val TABLE_NAME = "UserTweetRelationTable"
    val USER_UID = "userUid"
    val TWEETUID = "tweetUid"
}
