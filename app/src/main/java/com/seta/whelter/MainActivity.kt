package com.seta.whelter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.seta.common.extensions.logD
import com.seta.whelter.mvpViews.WeiboAuthMvpView
import com.seta.whelter.presenters.WeiboAuthPresenter
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), WeiboAuthMvpView {
    val mPresenter = WeiboAuthPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    fun onClick(view: View) {
        mPresenter.goAuth(this)
    }


    override fun onAuthSuccess() {
        logD("On Auth success")
        toast("授权成功")
    }

    override fun onAuthFail(t: Throwable) {
        logD("On Auth fail")
    }

    override fun onAuthCanceled() {
        logD("On auth canceled")
    }
}
