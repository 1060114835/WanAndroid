package com.chen.wanandroid.architecture.global

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import com.chen.wanandroid.architecture.function.ScreenSize
import com.chen.wanandroid.architecture.global.state.Config
import java.util.concurrent.ConcurrentHashMap


object Global {
    @JvmStatic
    val globalConfig = ConcurrentHashMap<String, Any>()

    fun init(appContext: Application) {
        val screenSize = ScreenSize(appContext)
        globalConfig[Config.SCREEN_WIDTH.string] = screenSize.screenWidth
        globalConfig[Config.SCREEN_HEIGHT.string] = screenSize.screenHeight
        globalConfig[Config.APP.string] = appContext
    }

    /**
     * 添加全局activity生命周期监听
     * 在应用开始的时候使用，否则失效
     */
    fun addGlobalLifecycleObserver(observer: LifecycleObserver) {
        globalConfig[Config.LIFECYCLE_OBSERVER.string] = observer
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> config(config: Config): T {
        return globalConfig[config.string] as T ?: throw GlobalConfigNotFoundException(config)
    }

    class GlobalConfigNotFoundException(config: Config) :
        Exception("The Global Config Not Found! (${config.string}) has not been added ")
}


