package com.chen.wanandroid.architecture.global.state

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

//单例
internal object NetStateManager : LifecycleObserver {


    /**
     * 开启网络状态监听
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connectListener() {

    }


    /**
     * 断开网络状态的监听
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disConnectListener() {

    }
}