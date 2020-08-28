package com.chen.wanandroid.architecture.mvvm

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import com.chen.wanandroid.architecture.BR
import com.chen.wanandroid.architecture.BaseApplication
import com.chen.wanandroid.architecture.R
import com.chen.wanandroid.architecture.function.BarUtils
import com.chen.wanandroid.architecture.global.state.Config
import com.chen.wanandroid.architecture.global.Global
import com.chen.wanandroid.architecture.global.state.NetStateManager
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions


/**
 * v层：一个页面只绑定一个StateViewModel和一个ActionHandler，分别处理状态和事件
 */
abstract class BaseActivity<T : StateViewModel> :
    AppCompatActivity(),
    EasyPermissions.PermissionCallbacks {

    protected val viewModel by lazy { initViewModel() }
    protected val actionHandler: ActionHandler? by lazy { initActionHandler() }

    protected val activityViewModelProvider: ViewModelProvider by lazy { ViewModelProvider(this) }

    protected val appViewModelProvider: ViewModelProvider by lazy {
        ViewModelProvider(Global.config<BaseApplication>(Config.APP), appViewModelFactory)
    }

    private val appViewModelFactory: ViewModelProvider.AndroidViewModelFactory by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(Global.config<BaseApplication>(Config.APP))
    }

    protected abstract fun initViewModel(): T

    /**
     *  通过BR 绑定页面的ViewModel和ActionHandler：
     */
    protected abstract fun configureDataBinding(): ViewDataBinding

    protected abstract fun initActionHandler(): ActionHandler?

    /**
     * @return 当前页面需要请求的动态权限
     */
    protected open fun configurePermission(): Array<String>? = null

    /**
     * 配置标题栏
     */
    protected open fun configureTitleBar(actionBar: View?) {

    }

    /**
     *  配置状态栏
     */
    protected open fun configureStatueBar() {
        BarUtils.setStatusBarColor(this, Color.WHITE)
        BarUtils.setStatusBarLightMode(this, true)
    }

    /**
     * 视图初始化
     */
    protected open fun initView() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewDataBinding = configureDataBinding()
        setContentView(viewDataBinding.root)
        viewDataBinding.lifecycleOwner = this
        init()
    }

    override fun onStart() {
        super.onStart()
        configurePermission()?.let {
            requestPermissions()
        }
    }

    private fun init() {
        configureObserver()
        configureStatueBar()
        initTitleBar()
        configureTitleBar(supportActionBar?.customView)
        initView()
        viewModel.loadData()
    }


    private fun configureObserver() {
        lifecycle.addObserver(NetStateManager)
        if (Global.globalConfig.contains(Config.LIFECYCLE_OBSERVER.string)) {
            val observer = Global.config<LifecycleObserver>(Config.LIFECYCLE_OBSERVER)
            lifecycle.addObserver(observer)
        }
    }


    /**
     * *********** 统一动态权限请求相关 **********
     */

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    @AfterPermissionGranted(66)
    private fun requestPermissions() {
        val permissions = configurePermission()!!
        if (!EasyPermissions.hasPermissions(this, *permissions)) {
            EasyPermissions.requestPermissions(
                this,
                resources.getString(R.string.perm_err_info), 66, *permissions
            )
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        configurePermission()?.let {
            if (EasyPermissions.somePermissionPermanentlyDenied(this, it.toList())) {
                AppSettingsDialog.Builder(this).build().show()
            }
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    /**
     * *********** 统一标题栏处理 **********
     */

    private fun initTitleBar() {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater,
            R.layout.bar, null, false
        )
        binding.lifecycleOwner = this
        binding.setVariable(BR.vm, viewModel)
        binding.setVariable(BR.action, actionHandler)
        binding.executePendingBindings()
        val lp = ActionBar.LayoutParams(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.MATCH_PARENT,
            Gravity.CENTER
        )
        val mActionBarView = binding.root
        mActionBarView.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        mActionBarView.z = 0f
        supportActionBar?.elevation = 0f
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setCustomView(mActionBarView, lp)
            actionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            actionBar.setDisplayShowCustomEnabled(true)
            actionBar.setDisplayShowHomeEnabled(false)
            actionBar.setDisplayShowTitleEnabled(false)
        }
    }


}