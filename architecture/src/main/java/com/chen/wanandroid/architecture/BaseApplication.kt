package com.chen.wanandroid.architecture

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.chen.wanandroid.architecture.global.Global

open class BaseApplication : Application(), ViewModelStoreOwner {
    private val viewModelStore = ViewModelStore()

    override fun onCreate() {
        super.onCreate()
        Global.init(this)
    }

    override fun getViewModelStore(): ViewModelStore = viewModelStore
}