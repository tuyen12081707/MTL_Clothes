package com.example.mtl_clothes.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mtl_clothes.R
import com.example.mtl_clothes.databinding.ActivityIntroBinding
import com.example.mtl_clothes.ultis.Common
import com.example.mtl_clothes.viewmodel.CommonVM

class IntroActivity : BaseActivity<ActivityIntroBinding,CommonVM>() {
    override fun initView() {

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK))

        }
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this,ResgiterActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK))

        }
    }

    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
    }

    override fun initViewBinding(): ActivityIntroBinding {
        return ActivityIntroBinding.inflate(layoutInflater,null,false)
    }

}