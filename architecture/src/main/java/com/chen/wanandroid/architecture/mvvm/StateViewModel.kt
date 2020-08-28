package com.chen.wanandroid.architecture.mvvm

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel


/**
 * 负责持有view的状态，通过dataBinding与视图绑定
 */
abstract class StateViewModel : ViewModel() {

    //页面标题
    val title: ObservableField<String> = ObservableField()

    abstract fun loadData()

}