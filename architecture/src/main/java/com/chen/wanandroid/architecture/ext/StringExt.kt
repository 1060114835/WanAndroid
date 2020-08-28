package com.chen.wanandroid.architecture.ext

import android.content.Context
import android.widget.Toast
import com.chen.wanandroid.architecture.function.CopyUtil
import com.chen.wanandroid.architecture.function.Log


fun String.log(tag: String = "") {
    Log.d("debug : $tag", this)
}

fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun Context.toast(content: String) {
    content.toast(this)
}

fun String.copy(context: Context) {
    CopyUtil.copy(context,this)
}


