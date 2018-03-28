package com.seta.whelter.http.apis

import com.seta.whelter.http.RateLimitPojo
import com.seta.whelter.http.TimelinePojo
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by SETA_WORK on 2018/3/19.
 */
interface WeiboApi {
    /**
     * 获取微博时间线
     * @param sinceId 若指定此参数，则返回ID比since_id大的微博（即比since_id时间晚的微博），默认为0。
     * @param max_id 若指定此参数，则返回ID小于或等于max_id的微博，默认为0。
     * @param feature 过滤类型ID，0：全部、1：原创、2：图片、3：视频、4：音乐，默认为0。
     * @param trim_user 返回值中user字段开关，0：返回完整user字段、1：user字段仅返回user_id，默认为0
     */
    @GET("/2/statuses/user_timeline.json")
    fun getTimeLine(@Query("access_token") token: String
                    , @Query("uid") uid: String
                    , @Query("page") page: Int
                    , @Query("count") count: Int
                    , @Query("since_id") sinceId: Int = 0
                    , @Query("max_id") max_id: Int = 0
                    , @Query("feature") feature: Int = 0
                    , @Query("trim_user") trim_user: Int = 0): Observable<TimelinePojo>

//    http://api.t.sina.com.cn/statuses/user_timeline.(json%7cxml)
//    @GET("/")

    @GET("/2/account/rate_limit_status")
    fun getRateLimit(@Query("access_token") token: String): Observable<RateLimitPojo>
}