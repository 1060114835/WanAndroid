package com.chen.wanandroid.architecture.net

import retrofit2.Call
import retrofit2.http.GET

interface Service<T> {
    @GET()
    fun get(): Call<RootBean<T>>
}