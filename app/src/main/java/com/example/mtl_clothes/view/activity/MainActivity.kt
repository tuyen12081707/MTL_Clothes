package com.example.mtl_clothes.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mtl_clothes.R
import com.example.mtl_clothes.databinding.ActivityMainBinding
import com.example.mtl_clothes.viewmodel.CommonVM
import com.example.mtl_clothes.viewmodel.MainVM

class MainActivity : BaseActivity<ActivityMainBinding, MainVM>() {

    override fun initView() {

    }

    override fun getClassVM(): Class<MainVM> {
        return MainVM::class.java
    }

    override fun initViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}