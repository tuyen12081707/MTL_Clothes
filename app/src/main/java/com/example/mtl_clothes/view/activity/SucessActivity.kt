package com.example.mtl_clothes.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mtl_clothes.R
import com.example.mtl_clothes.databinding.ActivitySucessBinding
import com.example.mtl_clothes.ultis.Common
import com.example.mtl_clothes.viewmodel.CommonVM

class SucessActivity : BaseActivity<ActivitySucessBinding, CommonVM>() {


    override fun initView() {
        binding.ivBack.setOnClickListener {
            finish()
        }
        Common.countRate++
        binding.tvBackToHome.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
        binding.btnContinue.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
        binding.btnTrackOrder.setOnClickListener {
            startActivity(Intent(this@SucessActivity, TrackOrderActivity::class.java))
        }
    }

    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
    }

    override fun initViewBinding(): ActivitySucessBinding {
        return ActivitySucessBinding.inflate(layoutInflater)
    }
}