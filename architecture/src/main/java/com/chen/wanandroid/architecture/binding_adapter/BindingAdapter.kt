package com.chen.wanandroid.architecture.binding_adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.chen.wanandroid.architecture.function.NoDoubleOnClickListener
import com.chen.wanandroid.architecture.mvvm.ActionHandler

/**
 * 全局通用BindingAdapter绑定
 */

@BindingAdapter("url")
fun src(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}

@BindingAdapter("src")
fun src(imageView: ImageView, resourceId: Int) {
    Glide.with(imageView.context).load(resourceId).into(imageView)
}


@BindingAdapter("onClick")
fun onClick(view: View, actionHandler: ActionHandler) {
    view.setOnClickListener(object : NoDoubleOnClickListener() {
        override fun noDoubleOnClick(view: View) {
            actionHandler.onClick(view)
        }
    })
}
