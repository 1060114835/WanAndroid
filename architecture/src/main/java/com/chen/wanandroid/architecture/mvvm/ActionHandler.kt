package com.chen.wanandroid.architecture.mvvm

import android.view.View
import androidx.lifecycle.ViewModel


/**
 * 处理每一个页面的事件，一般写为 activity 的 inner class，方便处理数据
 */
interface ActionHandler {

    fun onClick(view: View)
}