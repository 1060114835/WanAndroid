package com.chen.wanandroid.architecture.net.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import kotlin.collections.LinkedHashMap

internal abstract class BaseInterceptor : Interceptor {

    protected fun getUrlParameters(chain: Interceptor.Chain): LinkedHashMap<String, String?> {
        // 拿到请求的url
        val url = chain.request().url
        // 获取请求参数的个数
        val size = url.querySize
        val params = LinkedHashMap<String, String?>()
        for (i in 0 until size) {
            // 获取参数名和参数值并且添加到map里
            params[url.queryParameterName(i)] = url.queryParameterValue(i)
        }
        return params
    }

    protected fun getUrlParameters(chain: Interceptor.Chain, key: String) =
        getUrlParameters(chain)[key]


    override fun intercept(chain: Interceptor.Chain): Response {
        chain
    }
}