package com.example.mtl_clothes.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mtl_clothes.R
import com.example.mtl_clothes.databinding.ActivityOrderCheckingDetailBinding
import com.example.mtl_clothes.viewmodel.CommonVM

class OrderCheckingDetailActivity : BaseActivity<ActivityOrderCheckingDetailBinding,CommonVM>() {


    override fun initView() {

    }

    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
    }

    override fun initViewBinding(): ActivityOrderCheckingDetailBinding {
        return ActivityOrderCheckingDetailBinding.inflate(layoutInflater)
    }
}