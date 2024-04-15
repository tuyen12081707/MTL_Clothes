package com.example.mtl_clothes.view.activity

import android.content.Intent
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
import com.example.mtl_clothes.ultis.Common
import com.example.mtl_clothes.ultis.Common.countRate
import com.example.mtl_clothes.viewmodel.CommonVM

class SplashActivity : BaseActivity<ActivitySplashBinding, CommonVM>() {
    override fun initView() {
        Common.listOrder.clear()
        countRate = 1
        Handler(Looper.getMainLooper()).postDelayed({
            if(Common.getUserId(this)!=""){
                startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK))

            }else{
                startActivity(Intent(this,IntroActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK))
            }

        }, 3000)
    }

    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
    }

    override fun initViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater, null, false)
    }


}