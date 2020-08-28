package com.chen.wanandroid.architecture.net

import android.content.Context
import com.chen.wanandroid.architecture.global.state.Config
import com.chen.wanandroid.architecture.global.Global
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


internal object RetrofitHolder {

    private val okHttpLoggingInterceptor = HttpLoggingInterceptor()
    init {
        okHttpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    /**
     *  OkHttp 网络缓存： 20 MB
     */
    private var cache = Cache(
        File(Global.config<Context>(Config.APP).cacheDir, "HttpCache"),
        1024 * 1024 * 20
    )

    internal val okHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .retryOnConnectionFailure(true)
        .addInterceptor(okHttpLoggingInterceptor)
        .build()

    internal val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()

}