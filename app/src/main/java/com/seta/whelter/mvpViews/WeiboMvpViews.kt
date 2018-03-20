package com.seta.whelter.mvpViews

import com.seta.common.mvp.MvpView
import com.seta.whelter.http.TimelineBean

/**
 * Created by SETA_WORK on 2018/3/13.
 */
interface WeiboAuthMvpView : MvpView {
    fun onAuthSuccess(token: String, uid: String)
    fun onAuthFail(t: Throwable)
    fun onAuthCanceled()
}

interface WeiboTimelineMvpView : MvpView {
    fun onTimelineLoaded(posts: List<TimelineBean>)
    fun onTimelineLoadFail(t: Throwable)
}