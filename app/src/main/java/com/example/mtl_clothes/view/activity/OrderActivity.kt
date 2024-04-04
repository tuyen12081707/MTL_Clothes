package com.example.mtl_clothes.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mtl_clothes.R
import com.example.mtl_clothes.base_interface.IOrder
import com.example.mtl_clothes.databinding.ActivityOrderBinding
import com.example.mtl_clothes.model.ProductModel
import com.example.mtl_clothes.ultis.Common
import com.example.mtl_clothes.view.adapter.OrderAdapter
import com.example.mtl_clothes.viewmodel.CommonVM

class OrderActivity : BaseActivity<ActivityOrderBinding,CommonVM>() {
    private lateinit var orderAdapter: OrderAdapter
    override fun initView() {

        orderAdapter = OrderAdapter(this,object:IOrder{
            override fun callBackOrder(productModel: ProductModel) {

            }

            override fun callbackDelete(position: Int) {
                Common.listOrder.removeAt(position)
                orderAdapter.updateData(Common.listOrder)
            }

        })
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.rcvOrder.adapter = orderAdapter
        orderAdapter.updateData(Common.listOrder)
    }

    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
    }

    override fun initViewBinding(): ActivityOrderBinding {
        return ActivityOrderBinding.inflate(layoutInflater)
    }

}