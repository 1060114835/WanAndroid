package com.chen.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.chen.wanandroid.architecture.net.RetrofitHolder
import com.chen.wanandroid.model.Service
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loginService = RetrofitHolder.retrofit.create(Service::class.java)
        lifecycleScope.launch {
            Log.e("TAG", "onCreate: ", )
            val data = loginService.get().data
            Log.e("dong", "onCreate: ${Thread.currentThread()}  $data")
        }
    }
}