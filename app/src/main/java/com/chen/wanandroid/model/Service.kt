package com.chen.wanandroid.model

import com.chen.wanandroid.VXAccountBean
import com.chen.wanandroid.architecture.net.VX_ACCOUNT_LIST
import com.chen.wanandroid.model.bean.RootBean
import retrofit2.http.GET

interface Service {
    @GET(VX_ACCOUNT_LIST)
    suspend fun get(): RootBean<VXAccountBean>
}