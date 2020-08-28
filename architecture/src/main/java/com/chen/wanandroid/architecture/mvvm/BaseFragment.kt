package com.chen.wanandroid.architecture.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

abstract class BaseFragment : Fragment() {

    val viewDataBinding: ViewDataBinding
        get() = _viewDataBinding

    val viewModel by lazy { initViewModel() }
    val actionHandler by lazy { initActionHandler() }

    val activityViewModelProvider: ViewModelProvider
        get() = _activityViewModelProvider

    val fragmentViewModelProvider: ViewModelProvider by lazy { ViewModelProvider(this) }

    protected abstract fun initViewModel() : StateViewModel

    protected abstract fun configureVariable()

    protected abstract fun configureLayoutId() : Int

    protected abstract fun initActionHandler() : ActionHandler

    protected abstract fun loadData()

    private lateinit var _activityViewModelProvider : ViewModelProvider

    private lateinit var _viewDataBinding: ViewDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewDataBinding = DataBindingUtil.inflate(inflater,
            configureLayoutId(), container, false)
        _viewDataBinding.lifecycleOwner = this
        configureVariable()
        loadData()
        return _viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelStoreOwner : ViewModelStoreOwner = activity!!
        _activityViewModelProvider = ViewModelProvider(viewModelStoreOwner)
    }
}