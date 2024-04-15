package com.example.mtl_clothes.view.activity

import android.content.Intent
import android.widget.Toast
import com.example.mtl_clothes.databinding.ActivityLoginBinding
import com.example.mtl_clothes.ultis.Common
import com.example.mtl_clothes.viewmodel.LoginVM

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginVM>() {
    var isClick = true
    override fun initView() {
        var isComeOrder = intent.getBooleanExtra(Common.KEY_COME_ORDER, false)
        binding.btnLogin.setOnClickListener {
        if(isClick){
            if (binding.edtEmail.text.toString()
                    .trim() != "" && binding.edtPassword.text.toString().trim() != ""
            ) {
                isClick = false
                var email = binding.edtEmail.text.toString()
                var password = binding.edtPassword.text.toString()
                viewModel.postLogin(this@LoginActivity, email, password)

            } else {
                Toast.makeText(this, "Invalid email or password!", Toast.LENGTH_SHORT).show()
            }
        }

        }
        viewModel.liveDataLogin.observe(this) {
            if (it != null) {
                isClick = true
                Common.setBearerToken(this@LoginActivity, it.token)
                Common.setUserID(this@LoginActivity,it.user.id)
                Common.saveLoginRes(this,it)
                if (!isComeOrder) {
                    startActivity(
                        Intent(
                            this,
                            MainActivity::class.java
                        ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    )
                } else {
                    finish()
                }
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

    override fun getClassVM(): Class<LoginVM> {
        return LoginVM::class.java
    }

    override fun initViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater, null, false)
    }


}