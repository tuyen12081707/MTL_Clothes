package com.example.mtl_clothes.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mtl_clothes.R
import com.example.mtl_clothes.databinding.ActivityResgiterBinding
import com.example.mtl_clothes.viewmodel.CommonVM

class ResgiterActivity : BaseActivity<ActivityResgiterBinding, CommonVM>() {


    override fun initView() {

    }

    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
    }

    override fun initViewBinding(): ActivityResgiterBinding {
        return ActivityResgiterBinding.inflate(layoutInflater)
    }
}