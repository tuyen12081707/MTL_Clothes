package com.example.mtl_clothes.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mtl_clothes.R
import com.example.mtl_clothes.databinding.ActivityIntroBinding
import com.example.mtl_clothes.databinding.ActivityLoginBinding
import com.example.mtl_clothes.ultis.Common
import com.example.mtl_clothes.viewmodel.CommonVM

class LoginActivity : BaseActivity<ActivityLoginBinding, CommonVM>() {
    override fun initView() {
        binding.btnLogin.setOnClickListener {
            if (binding.edtEmail.text.toString()
                    .trim() == "Admin" && binding.edtPassword.text.toString().trim() == "123456"
            ) {
                Common.setLoginSuccess(this, true)
                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                )
            } else {
                Toast.makeText(this, "Invalid email or password!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.lnRegister.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ResgiterActivity::class.java
                )
            )
        }
    }

    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
    }

    override fun initViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater, null, false)
    }


}