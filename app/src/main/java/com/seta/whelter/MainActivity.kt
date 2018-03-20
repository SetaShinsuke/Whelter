package com.seta.whelter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.seta.common.extensions.DelegateExt
import com.seta.common.extensions.logD
import com.seta.whelter.http.TimelineBean
import com.seta.whelter.mvpViews.WeiboAuthMvpView
import com.seta.whelter.mvpViews.WeiboTimelineMvpView
import com.seta.whelter.presenters.WeiboAuthPresenter
import com.seta.whelter.presenters.WeiboTimelinePresenter
import com.seta.whelter.utils.Constants
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), WeiboAuthMvpView, WeiboTimelineMvpView {

    private var mWBToken by DelegateExt.preference(this, Constants.WEIBO_TOKEN, "")
    private var mWBUid by DelegateExt.preference(this, Constants.WEIBO_UID, "")
    val mAuthPresenter = WeiboAuthPresenter()
    val mTimelinePresenter = WeiboTimelinePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuthPresenter.attachView(this)
        mTimelinePresenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mAuthPresenter.detachView()
        mTimelinePresenter.detachView()
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.mBtnGoAuth -> {
                if (mWBToken.isNotBlank()) {
                    mTimelinePresenter.loadWeiboTimeline(mWBToken, mWBUid)
                } else {
                    mAuthPresenter.goAuth(this)
                }
            }
        }
    }

    override fun onTimelineLoaded(posts: List<TimelineBean>) {
        logD("微博加载成功")
    }

    override fun onTimelineLoadFail(t: Throwable) {
        logD("微博加载失败")
    }

    override fun onAuthSuccess(token: String, uid: String) {
        this.mWBToken = token
        this.mWBUid = uid
        logD("On Auth success: $token")
        toast("授权成功")
        mTimelinePresenter.loadWeiboTimeline(token, uid)
    }

    override fun onAuthFail(t: Throwable) {
        logD("On Auth fail")
    }

    override fun onAuthCanceled() {
        logD("On auth canceled")
    }
}
