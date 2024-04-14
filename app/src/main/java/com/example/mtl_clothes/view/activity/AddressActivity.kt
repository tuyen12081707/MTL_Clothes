package com.example.mtl_clothes.view.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mtl_clothes.R
import com.example.mtl_clothes.databinding.ActivityAddressBinding
import com.example.mtl_clothes.ultis.Common
import com.example.mtl_clothes.viewmodel.CommonVM

class AddressActivity : BaseActivity<ActivityAddressBinding, CommonVM>() {
    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
    }

    override fun initViewBinding(): ActivityAddressBinding {
        return ActivityAddressBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.ivBack.setOnClickListener {
            finish()
        }
        if (Common.getShippingAddress(this) != "") {
            binding.edtShippingAddress.setText(Common.getShippingAddress(this))
        }
        if(Common.getShippingCity(this)!=""){
            binding.edtShippingCity.setText(Common.getShippingCity(this))
        }
        if(Common.getTelephone(this)!=""){
            binding.edtShippingCity.setText(Common.getTelephone(this))
        }
        if(Common.getPromoCode(this)!=""){
            binding.edtDiscountCode.setText(Common.getPromoCode(this))
        }
        binding.btnSave.setOnClickListener {
            handleSave()
        }

    }

    private fun handleSave() {
        if(binding.edtShippingAddress.text.trim().isNotEmpty()&& binding.edtShippingCity.text.trim().isNotEmpty()
            && binding.edtTelephone.text.trim().isNotEmpty()){
            binding.tvError.visibility = View.GONE
            Common.setShippingAddress(this,binding.edtShippingAddress.text.toString())
            Common.setShippingCity(this,binding.edtShippingCity.text.toString())
            Common.setTelphone(this,binding.edtTelephone.text.toString())
            if(binding.edtDiscountCode.text.trim().isNotEmpty()){
                Common.setPromoCode(this,binding.edtDiscountCode.text.toString())
            }
            setResult(RESULT_OK)
            finish()
        }else{
            binding.tvError.visibility = View.VISIBLE

        }
    }

}