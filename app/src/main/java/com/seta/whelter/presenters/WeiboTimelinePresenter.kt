package com.seta.whelter.presenters

import com.seta.common.extensions.logD
import com.seta.common.extensions.logW
import com.seta.common.http.Network
import com.seta.common.mvp.BasePresenter
import com.seta.whelter.http.TimelineBean
import com.seta.whelter.mvpViews.WeiboTimelineMvpView
import rx.Subscriber

/**
 * Created by SETA_WORK on 2018/3/19.
 */
class WeiboTimelinePresenter : BasePresenter<WeiboTimelineMvpView>() {
    companion object {
        val LIMIT = 5
    }


    fun loadWeiboTimeline(token: String, uid: String, page: Int = 1) {
        Network.weiboApi.getTimeLine(token, uid, page, LIMIT)
                .map { it.statuses }
                .doSubscribe(object : Subscriber<List<TimelineBean>>() {
                    override fun onCompleted() {
                    }

                    override fun onNext(t: List<TimelineBean>) {
                        logD("load Weibo timeline onNext: $t")
                    }

                    override fun onError(e: Throwable?) {
                        logW("load Weibo error: ${e?.message}")
                    }

                })
    }
}