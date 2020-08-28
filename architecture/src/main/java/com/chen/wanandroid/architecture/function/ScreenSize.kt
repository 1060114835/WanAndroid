package com.chen.wanandroid.architecture.function

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

internal class ScreenSize(context: Context) {
    val screenWidth: Int
    val screenHeight: Int

    init {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        screenWidth = dm.widthPixels;// 获取屏幕分辨率宽度
        screenHeight = dm.heightPixels;// 获取屏幕分辨率高度
    }
}