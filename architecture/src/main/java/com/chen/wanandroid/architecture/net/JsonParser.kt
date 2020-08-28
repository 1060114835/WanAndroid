package com.chen.wanandroid.architecture.net

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object JsonParser {
    val jsonParser: Gson = GsonBuilder().setPrettyPrinting().serializeNulls().create()
}