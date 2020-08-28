package com.chen.wanandroid.architecture.net

import okhttp3.Interceptor

object Restful {


    fun addInterceptor(interceptor: Interceptor): Restful {
        interceptors.add(interceptor)
        return this
    }

    fun addNetInterceptor(netInterceptor: Interceptor): Restful {
        netInterceptors.add(netInterceptor)
        return this
    }


}