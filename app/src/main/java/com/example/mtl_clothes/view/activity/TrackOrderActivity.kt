package com.example.mtl_clothes.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mtl_clothes.databinding.ActivityTrackOrderBinding
import com.example.mtl_clothes.ultis.Common
import com.example.mtl_clothes.view.adapter.OrderTrackAdapter
import com.example.mtl_clothes.viewmodel.TrackVM

class TrackOrderActivity : BaseActivity<ActivityTrackOrderBinding, TrackVM>() {
    private lateinit var adapter: OrderTrackAdapter
    override fun initView() {
        binding.ivBack.setOnClickListener{
            finish()
        }
        adapter = OrderTrackAdapter(this)
        binding.rcvOrderTrack.adapter = adapter
        Common.getUserId(this)?.let { viewModel.getAllOrder(this, it) }
        viewModel.liveData.observe(this) {
            if (it != null) {
                if (it.listOrders != null) {
                    try {
                        var listproduct = it.listOrders!![0].products
                        if (listproduct?.isNotEmpty() == true) {
                            for (product in listproduct) {
                                viewModel.getProductById(this@TrackOrderActivity, product.productId)
                            }
                        }
                    } catch (e: Exception) {
                    }
                    adapter.updateData(it.listOrders!!)
                }
            }
        }

    }

    override fun getClassVM(): Class<TrackVM> {
        return TrackVM::class.java
    }

    override fun initViewBinding(): ActivityTrackOrderBinding {
        return ActivityTrackOrderBinding.inflate(layoutInflater)
    }

}