package com.example.mtl_clothes.view.activity

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mtl_clothes.R
import com.example.mtl_clothes.api.res.ProductRes
import com.example.mtl_clothes.database.DatabaseRepository
import com.example.mtl_clothes.database.model.OrderModel
import com.example.mtl_clothes.database.callback.InsertCallBack
import com.example.mtl_clothes.database.model.ProductModel
import com.example.mtl_clothes.databinding.ActivityProductDetailBinding
import com.example.mtl_clothes.ultis.Common
import com.example.mtl_clothes.ultis.ConvertCurrency
import com.example.mtl_clothes.viewmodel.ProductDetailVM

class ProductDetail : BaseActivity<ActivityProductDetailBinding, ProductDetailVM>(),
    View.OnClickListener {
    private var productDetail: ProductRes? = null
    private var listSize: MutableList<String> = mutableListOf("S", "M", "L", "XL", "XXL")
    private var quantity = 10
    var isSizeS = true
    var isSizeM = true
    var isSizeL = true
    var isSizeXL = true
    var isSizeXXL = true
    override fun initView() {
        binding.tvName.isSelected = true
        binding.tvPrices.isSelected = true
        var productId = intent.getStringExtra(Common.KEY_PRODUCT_ORDER)
        productId?.let { Log.d("productid==", it) }
        if (productId != null) {
            viewModel.getAllProduct(this, productId!!)
        }
        showDialog(this)

        viewModel.productDetail.observe(this) {
            if (it != null) {
                productDetail = it
                disableDialog(this)
                binding.tvCategories.text = it?.category
                binding.tvName.text = it?.name
                try {
                    Glide.with(this)
                        .load(it?.photos?.get(0))
                        .centerCrop()
                        .into(binding.ivClothes)
                } catch (e: Exception) {
                    Glide.with(this)
                        .load(R.drawable.img_clothes)
                        .centerCrop()
                        .into(binding.ivClothes)
                }
                binding.tvPrices.text =
                    it.price.toFloat()
                        .let { it1 -> ConvertCurrency.getInstance().convertToUSD(it1) }
                binding.tvContent.text = it.description
                binding.ivIncrease.setOnClickListener {
                    var temp = binding.tvNumber.text.toString().toInt()
                    temp++
                    binding.tvNumber.text = temp.toString()

                }
                quantity = it.quantity
                binding.ivDecreases.setOnClickListener {
                    var temp = binding.tvNumber.text.toString().toInt()
                    temp--
                    binding.tvNumber.text = temp.toString()
                }
                it?.size?.let { it1 -> listSize.addAll(it1) }

            }
        }
        for (size in listSize) {
            isSizeS = size.trim() == "S"
            isSizeM = size.trim() == "M"
            isSizeL = size.trim() == "L"
            isSizeXL = size.trim() == "XL"
            isSizeXXL = size.trim() == "XXL"
        }
        binding.btnAdd.setOnClickListener(this)
        binding.tvSizeS.setOnClickListener(this)
        binding.tvSizeM.setOnClickListener(this)
        binding.tvSizeL.setOnClickListener(this)
        binding.tvSizeXl.setOnClickListener(this)
        binding.tvSizeXxl.setOnClickListener(this)
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun handleCheckStatusSize() {
        if (isSizeS) {
            binding.tvSizeS.isEnabled = isSizeS
        } else {
            binding.tvSizeS.isEnabled = isSizeS

        }
        if (isSizeL) {
            binding.tvSizeL.isEnabled = isSizeL

        } else {
            binding.tvSizeL.isEnabled = isSizeL

        }
        if (isSizeM) {
            binding.tvSizeM.isEnabled = isSizeM

        } else {
            binding.tvSizeM.isEnabled = isSizeM

        }
        if (isSizeXL) {
            binding.tvSizeXl.isEnabled = isSizeXL

        } else {
            binding.tvSizeXl.isEnabled = isSizeXL

        }
        if (isSizeXXL) {
            binding.tvSizeXxl.isEnabled = isSizeXXL

        } else {
            binding.tvSizeXxl.isEnabled = isSizeXXL

        }
    }

    override fun getClassVM(): Class<ProductDetailVM> {
        return ProductDetailVM::class.java
    }

    override fun initViewBinding(): ActivityProductDetailBinding {
        return ActivityProductDetailBinding.inflate(layoutInflater)
    }

    override fun onClick(view: View?) {
        if (view is TextView) {
            when (view.id) {
                R.id.tv_size_s -> {
                    binding.tvSizeS.setTextColor(Color.WHITE)
                    binding.tvSizeM.setTextColor(Color.BLACK)
                    binding.tvSizeL.setTextColor(Color.BLACK)
                    binding.tvSizeXl.setTextColor(Color.BLACK)
                    binding.tvSizeXxl.setTextColor(Color.BLACK)

                    binding.tvSizeS.setBackgroundResource(R.drawable.bg_corner_10)
                    binding.tvSizeM.setBackgroundResource(R.drawable.bg_custom_unactive)
                    binding.tvSizeL.setBackgroundResource(R.drawable.bg_custom_unactive)
                    binding.tvSizeXl.setBackgroundResource(R.drawable.bg_custom_unactive)
                    binding.tvSizeXxl.setBackgroundResource(R.drawable.bg_custom_unactive)
                }

                R.id.tv_size_m -> {
                    binding.tvSizeS.setTextColor(Color.BLACK)
                    binding.tvSizeM.setTextColor(Color.WHITE)
                    binding.tvSizeL.setTextColor(Color.BLACK)
                    binding.tvSizeXl.setTextColor(Color.BLACK)
                    binding.tvSizeXxl.setTextColor(Color.BLACK)
                    binding.tvSizeS.setBackgroundResource(R.drawable.bg_custom_unactive)
                    binding.tvSizeM.setBackgroundResource(R.drawable.bg_corner_10)
                    binding.tvSizeL.setBackgroundResource(R.drawable.bg_custom_unactive)
                    binding.tvSizeXl.setBackgroundResource(R.drawable.bg_custom_unactive)
                    binding.tvSizeXxl.setBackgroundResource(R.drawable.bg_custom_unactive)
                }

                R.id.tv_size_l -> {
                    binding.tvSizeS.setTextColor(Color.BLACK)
                    binding.tvSizeM.setTextColor(Color.BLACK)
                    binding.tvSizeL.setTextColor(Color.WHITE)
                    binding.tvSizeXl.setTextColor(Color.BLACK)
                    binding.tvSizeXxl.setTextColor(Color.BLACK)
                    binding.tvSizeS.setBackgroundResource(R.drawable.bg_custom_unactive)
                    binding.tvSizeM.setBackgroundResource(R.drawable.bg_custom_unactive)
                    binding.tvSizeL.setBackgroundResource(R.drawable.bg_corner_10)
                    binding.tvSizeXl.setBackgroundResource(R.drawable.bg_custom_unactive)
                    binding.tvSizeXxl.setBackgroundResource(R.drawable.bg_custom_unactive)
                }

                R.id.tv_size_xl -> {
                    binding.tvSizeS.setTextColor(Color.BLACK)
                    binding.tvSizeM.setTextColor(Color.BLACK)
                    binding.tvSizeL.setTextColor(Color.BLACK)
                    binding.tvSizeXl.setTextColor(Color.WHITE)
                    binding.tvSizeXxl.setTextColor(Color.BLACK)
                    binding.tvSizeS.setBackgroundResource(R.drawable.bg_custom_unactive)
                    binding.tvSizeM.setBackgroundResource(R.drawable.bg_custom_unactive)
                    binding.tvSizeL.setBackgroundResource(R.drawable.bg_custom_unactive)
                    binding.tvSizeXl.setBackgroundResource(R.drawable.bg_corner_10)
                    binding.tvSizeXxl.setBackgroundResource(R.drawable.bg_custom_unactive)
                }

                R.id.tv_size_xxl -> {
                    binding.tvSizeS.setTextColor(Color.BLACK)
                    binding.tvSizeM.setTextColor(Color.BLACK)
                    binding.tvSizeL.setTextColor(Color.BLACK)
                    binding.tvSizeXl.setTextColor(Color.BLACK)
                    binding.tvSizeXxl.setTextColor(Color.WHITE)
                    binding.tvSizeS.setBackgroundResource(R.drawable.bg_custom_unactive)
                    binding.tvSizeM.setBackgroundResource(R.drawable.bg_custom_unactive)
                    binding.tvSizeL.setBackgroundResource(R.drawable.bg_custom_unactive)
                    binding.tvSizeXl.setBackgroundResource(R.drawable.bg_custom_unactive)
                    binding.tvSizeXxl.setBackgroundResource(R.drawable.bg_corner_10)
                }


            }
        } else if (view is RelativeLayout) {
            if (view.id == R.id.btn_add) {
                var order = OrderModel()
                if (productDetail != null) {
                    order.quantity_order = binding.tvNumber.text.toString().trim().toInt()
                    order.id = productDetail!!.id
                    order.name = productDetail!!.name
                    order.typeOf = productDetail!!.typeOf
                    order.category = productDetail!!.category
                    order.description = productDetail!!.description
                    order.price = productDetail!!.price
                    order.quantity = productDetail!!.quantity
                    order.brand = productDetail!!.brand
                    order.photos = productDetail!!.photos
                    order.size = productDetail!!.size
                    order.createdAt = productDetail!!.createdAt
                    order.updatedAt = productDetail!!.updatedAt
                    order.version = productDetail!!.version
                }
                Log.d("order===", order.order_id.toString())

                DatabaseRepository.insertOrder(this, order, object : InsertCallBack {
                    override fun onSucess() {
                        startActivity(Intent(this@ProductDetail, OrderActivity::class.java))
                    }

                })
            }
        }


    }
}