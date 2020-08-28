package com.chen.wanandroid.architecture.function


import android.app.Activity
import android.app.Dialog
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import com.chen.wanandroid.architecture.R
import com.chen.wanandroid.architecture.global.state.Config
import com.chen.wanandroid.architecture.global.Global

object Dialog {

    var msgEt:EditText? = null

    fun show(
        activity: Activity,
        title: String,
        positive: (view: View) -> Unit,
        negative: (view: View) -> Unit = {},
        message: String? = null,
        hint: String? = null
    ) {
        val dialog = Dialog(activity, R.style.AlertDialogStyle)
        val view = View.inflate(activity, R.layout.dialog, null)
        val confirm = view.findViewById<TextView>(R.id.confirm_tv)
        val cancel = view.findViewById<TextView>(R.id.cancel_tv)
        val msgTv = view.findViewById<TextView>(R.id.msg_tv)
        msgEt = view.findViewById(R.id.msg_et)
        val titleTv = view.findViewById<TextView>(R.id.title_tv)
        val positiveWrapper = { v: View -> positive(v); dialog.cancel() }
        val negativeWrapper = { v: View -> negative(v); dialog.cancel() }

        if (hint != null) {
            msgTv.visibility = View.GONE
            msgEt?.visibility = View.VISIBLE
            msgEt?.hint = hint
        }
        msgTv.text = message
        titleTv.text = title
        cancel.setOnClickListener(negativeWrapper)
        confirm.setOnClickListener(positiveWrapper)
        dialog.setContentView(view)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        view.minimumHeight = ((Global.config<Int>(Config.SCREEN_HEIGHT) * 0.23).toInt())
        val window = dialog.window
        if (window != null) {
            val lp: WindowManager.LayoutParams = window.attributes
            lp.width = ((Global.config<Int>(Config.SCREEN_WIDTH) * 0.75).toInt())
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            lp.gravity = Gravity.CENTER
            window.attributes = lp
        }
        dialog.show()
    }
}