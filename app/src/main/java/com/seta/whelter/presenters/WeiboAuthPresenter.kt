package com.seta.whelter.presenters

import android.app.Activity
import com.seta.common.extensions.logD
import com.seta.common.mvp.BasePresenter
import com.seta.whelter.Weibo
import com.seta.whelter.mvpViews.WeiboAuthMvpView
import com.sina.weibo.sdk.WbSdk
import com.sina.weibo.sdk.auth.*
import com.sina.weibo.sdk.auth.sso.SsoHandler
import kotlin.properties.Delegates

/**
 * Created by SETA_WORK on 2018/3/13.
 */
class WeiboAuthPresenter : BasePresenter<WeiboAuthMvpView>() {
    private var mSsoHandler by Delegates.notNull<SsoHandler>()

    fun goAuth(activity: Activity) {
        val authInfo = AuthInfo(activity, Weibo.APP_KEY, Weibo.REDIRECT_URL, Weibo.SCOPE)
        WbSdk.install(activity, authInfo)
        val context = activity.applicationContext
        mSsoHandler = SsoHandler(activity)
        mSsoHandler.authorize(object : WbAuthListener {
            override fun onSuccess(oauth2AccessToken: Oauth2AccessToken?) {
                logD("authorize success : " + oauth2AccessToken)
                AccessTokenKeeper.writeAccessToken(context, oauth2AccessToken)
                val token = oauth2AccessToken?.getToken()
                val uid = oauth2AccessToken?.getUid()
                mvpView?.onAuthSuccess()
            }

            override fun onFailure(p0: WbConnectErrorMessage?) {
                logD("authorize fail, code: " + p0?.errorCode + ", message: " + p0?.errorMessage)
                mvpView?.onAuthFail(Exception("$p0"))
            }

            override fun cancel() {
                logD("authorize cancel.")
                mvpView?.onAuthCanceled()
            }

        })
    }
}