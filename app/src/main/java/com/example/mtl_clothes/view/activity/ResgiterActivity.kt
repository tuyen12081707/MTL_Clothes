package com.example.mtl_clothes.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mtl_clothes.R
import com.example.mtl_clothes.api.req.RegisterReq
import com.example.mtl_clothes.databinding.ActivityResgiterBinding
import com.example.mtl_clothes.viewmodel.CommonVM
import com.example.mtl_clothes.viewmodel.RegisterVM

class ResgiterActivity : BaseActivity<ActivityResgiterBinding, RegisterVM>() {


    override fun initView() {
        binding.imageView3.setOnClickListener {
            finish()
        }
        binding.btnRegister.setOnClickListener {
            var userName = binding.edtName.text.toString().trim()
            var password = binding.edtPassword.text.toString().trim()
            var email = binding.edtEmail.text.toString().trim()
            if(userName.trim().isNotEmpty()&& password.trim().isNotEmpty()&& email.isNotEmpty()){
                var register = RegisterReq(userName,email,password)
                viewModel.makeRegister(this@ResgiterActivity,register)
            }
        }
        viewModel.registerLive.observe(this){
            if(it!=null){
                Toast.makeText(this,"Register SuccessFully!",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@ResgiterActivity,LoginActivity::class.java))
                finish()
            }
        }
    }

    override fun getClassVM(): Class<RegisterVM> {
        return RegisterVM::class.java
    }

    override fun initViewBinding(): ActivityResgiterBinding {
        return ActivityResgiterBinding.inflate(layoutInflater)
    }
}