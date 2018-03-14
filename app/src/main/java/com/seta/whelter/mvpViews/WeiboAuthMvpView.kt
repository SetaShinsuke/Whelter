package com.seta.whelter.mvpViews

import com.seta.common.mvp.MvpView

/**
 * Created by SETA_WORK on 2018/3/13.
 */
interface WeiboAuthMvpView: MvpView{
    fun onAuthSuccess()
    fun onAuthFail(t:Throwable)
    fun onAuthCanceled()
}