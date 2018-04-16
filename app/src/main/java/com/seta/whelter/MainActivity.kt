package com.seta.whelter

import android.content.Intent
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
import com.seta.whelter.utils.Weibo
import com.sina.weibo.sdk.WbSdk
import com.sina.weibo.sdk.auth.*
import com.sina.weibo.sdk.auth.sso.SsoHandler
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), WeiboAuthMvpView, WeiboTimelineMvpView {

    private var mWBToken by DelegateExt.preference(this, Constants.WEIBO_TOKEN, "")
    private var mWBUid by DelegateExt.preference(this, Constants.WEIBO_UID, "")
    val mAuthPresenter = WeiboAuthPresenter()
    val mTimelinePresenter = WeiboTimelinePresenter()
    var mAuthInfo by Delegates.notNull<AuthInfo>()
    var mSsoHandler by Delegates.notNull<SsoHandler>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuthInfo = AuthInfo(this, Weibo.APP_KEY, Weibo.REDIRECT_URL, Weibo.SCOPE)
        WbSdk.install(this, mAuthInfo)
        mSsoHandler = SsoHandler(this)
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
//                if (mWBToken.isNotBlank()) {
//                    mTimelinePresenter.loadWeiboTimeline(mWBToken, mWBUid)
//                } else {
//                mAuthPresenter.goAuth(this)
//                }
                val mvpView = this
                val context = this
                mSsoHandler.authorize(object : WbAuthListener {
                    override fun onSuccess(oauth2AccessToken: Oauth2AccessToken?) {
                        logD("authorize success : " + oauth2AccessToken)
                        AccessTokenKeeper.writeAccessToken(context, oauth2AccessToken)
                        val token = oauth2AccessToken?.token
                        val uid = oauth2AccessToken?.uid
                        if (token != null && uid != null) {
                            mvpView.onAuthSuccess(token, uid)
                            return
                        }
                        mvpView.onAuthFail(Exception("Auth token null!"))
                    }

                    override fun onFailure(p0: WbConnectErrorMessage?) {
                        logD("authorize fail, code: " + p0?.errorCode + ", message: " + p0?.errorMessage)
                        mvpView.onAuthFail(Exception("$p0"))
                    }

                    override fun cancel() {
                        logD("authorize cancel.")
                        mvpView.onAuthCanceled()
                    }

                })
            }
            R.id.mBtnLoadPosts -> {
                mTimelinePresenter.loadWeiboTimeline(mWBToken, mWBUid)
            }
        }
    }

    override fun onTimelineLoaded(posts: List<TimelineBean>) {
        logD("微博加载成功, size: " + posts.size)
        var text = "加载成功,size: ${posts.size}"
        posts.forEach { text += "\n${it.id}" }
        mTvResult.text = text
    }

    override fun onTimelineLoadFail(t: Throwable) {
        logD("微博加载失败")
        toast("微博加载失败: ${t.message}")
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
        mSsoHandler.authorizeCallBack(requestCode, resultCode, data)
    }
}
