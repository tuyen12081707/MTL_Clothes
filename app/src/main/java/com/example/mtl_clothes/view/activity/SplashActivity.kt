package com.example.mtl_clothes.view.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mtl_clothes.R
import com.example.mtl_clothes.databinding.ActivitySplashBinding
import com.example.mtl_clothes.viewmodel.CommonVM

class SplashActivity : BaseActivity<ActivitySplashBinding, CommonVM>() {
    override fun initView() {
        Handler(Looper.getMainLooper()).postDelayed({

        },3000)
    }

    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
    }

    override fun initViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater,null,false)
    }


}