package com.example.mtl_clothes.view.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.viewbinding.ViewBinding
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import java.io.Serializable

abstract class BaseActivity<B : ViewBinding, M : ViewModel> : AppCompatActivity() {
    var dialog: ACProgressFlower? = null
    protected val binding: B by lazy { initViewBinding() }
    protected lateinit var viewModel: M
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[getClassVM()]
        setContentView(binding.root)
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        initView()
    }

    fun <T : Serializable?> getSerializable(intent: Intent, key: String, m_class: Class<T>): T {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra(key, m_class)!!
        else
            intent.getSerializableExtra(key) as T
    }

    abstract fun initView()

    abstract fun getClassVM(): Class<M>

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            val decorView = window.decorView
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }

    fun showDialog(activity: Activity) {
        dialog = ACProgressFlower.Builder(activity)
            .direction(ACProgressConstant.DIRECT_CLOCKWISE)
            .themeColor(Color.WHITE)
            .text("Loading")
            .fadeColor(Color.DKGRAY).build()
        if (dialog?.isShowing == false) {
            dialog?.show()
        }
    }

    fun disableDialog(activity: Activity) {
        if (dialog?.isShowing == true && !activity.isFinishing) {
            dialog?.dismiss()
        }
    }

    protected abstract fun initViewBinding(): B

}


