package com.chen.wanandroid.model.bean

data class RootBean<T>(
    val data: List<T>,
    val errorCode: Int,
    val errorMsg: String
)