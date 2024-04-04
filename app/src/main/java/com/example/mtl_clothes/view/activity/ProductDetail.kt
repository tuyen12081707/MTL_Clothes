package com.example.mtl_clothes.view.activity

import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.mtl_clothes.R
import com.example.mtl_clothes.databinding.ActivityProductDetailBinding
import com.example.mtl_clothes.model.ProductModel
import com.example.mtl_clothes.ultis.Common
import com.example.mtl_clothes.ultis.ConvertCurrency
import com.example.mtl_clothes.viewmodel.CommonVM

class ProductDetail : BaseActivity<ActivityProductDetailBinding, CommonVM>(), View.OnClickListener {
    private var productModel: ProductModel? = null
    override fun initView() {
         productModel =
            getSerializable(intent, Common.KEY_PRODUCT_ORDER, ProductModel::class.java)
        productModel.let {
            binding.tvCategories.text = productModel?.category
            binding.tvName.text = productModel?.name
            Glide.with(this)
                .load(productModel?.imageLink)
                .centerCrop()
                .into(binding.ivClothes)
            binding.tvPrices.text =
                ConvertCurrency.getInstance().convertToUSD(productModel!!.prices.trim().toFloat())
            binding.tvContent.text = productModel?.description
            binding.ivIncrease.setOnClickListener {
                var temp = binding.tvNumber.text.toString().toInt()
                temp++
                binding.tvNumber.text = temp.toString()

            }
            binding.ivDecreases.setOnClickListener {
                var temp = binding.tvNumber.text.toString().toInt()
                temp--
                binding.tvNumber.text = temp.toString()
            }
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

    override fun getClassVM(): Class<CommonVM> {
        return CommonVM::class.java
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
                productModel?.let { Common.listOrder.add(it) }
                startActivity(Intent(this,MainActivity::class.java))
                finish()

            }
        }


    }
}