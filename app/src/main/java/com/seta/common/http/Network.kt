package com.seta.common.http

import android.util.Log
import com.seta.common.utils.Constants
import com.seta.whelter.http.apis.WeiboApi
import com.seta.whelter.utils.Weibo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by SETA_WORK on 2017/7/3.
 */
object Network {

    private val gsonConverterFactory = GsonConverterFactory.create()!!
    private val rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create()!!

    private val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private val client: OkHttpClient by lazy {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Log.d(Constants.LOG_TAG_NETWORT, it) })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder
                .addInterceptor(interceptor)
                .build()
    }

    private val weiboRetrofit by lazy {
        Retrofit.Builder()
                .client(client)
                .baseUrl(Weibo.WEIBO_HOST)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build()
    }

    val weiboApi: WeiboApi by lazy { weiboRetrofit.create(WeiboApi::class.java) }
}