package com.chen.wanandroid.architecture.net

data class RootBean<T>(
    val data: List<T>,
    val errorCode: Int,
    val errorMsg: String
)