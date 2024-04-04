package com.example.mtl_clothes.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.GravityCompat
import com.example.mtl_clothes.R
import com.example.mtl_clothes.base_interface.IProduct
import com.example.mtl_clothes.databinding.ActivityMainBinding
import com.example.mtl_clothes.model.ProductModel
import com.example.mtl_clothes.ultis.Common
import com.example.mtl_clothes.ultis.setVisible
import com.example.mtl_clothes.view.adapter.CategoryAdapter
import com.example.mtl_clothes.view.adapter.ProductAdapter
import com.example.mtl_clothes.viewmodel.CommonVM
import com.example.mtl_clothes.viewmodel.MainVM
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class MainActivity : BaseActivity<ActivityMainBinding, MainVM>() {
    private lateinit var categoriesAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter

    var listCategories: MutableList<String> = mutableListOf(
        "Popular", "Mens", "Women", "Sales"
    )
    var listProduct: MutableList<ProductModel> = mutableListOf()
    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {

            }
        }

    override fun initView() {
        addSampleList()
        handleCategories()
        handleProduct()
        binding.ivMenu.setOnClickListener {
            openDrawer(true)
        }
    }

    fun openDrawer(isOpen: Boolean) {
        if (isOpen) {
            binding.drawerLayout.openDrawer(GravityCompat.END)
        } else {
            binding.drawerLayout.closeDrawer(GravityCompat.END)
        }
    }

    override fun onResume() {
        super.onResume()
        if(Common.listOrder.isNotEmpty()){
            binding.ivCountOrder.setVisible(true)
            binding.ivCountOrder.text = Common.listOrder.size.toString()
            binding.btnOrder.setOnClickListener {
                resultLauncher.launch(Intent(this,OrderActivity::class.java))
            }
        }else{
            binding.ivCountOrder.setVisible(false)

        }
    }

    private fun addSampleList() {
        listProduct.add(
            ProductModel(
                0,
                "Pink Hoodie",
                "40",
                false,
                "Geeta Mens",
                "https://ih1.redbubble.net/image.4373474571.0812/ssrco,classic_tee,womens,fafafa:ca443f4786,front_alt,square_product,600x600.jpg",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            )
        )
        listProduct.add(
            ProductModel(
                1,
                "Leather Jacket",
                "48",
                false,
                "Geeta Mens",
                "https://ih1.redbubble.net/image.5165706440.7185/ssrco,slim_fit_t_shirt,womens,101010:01c5ca27c6,front,square_product,600x600.jpg",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            )
        )
        listProduct.add(
            ProductModel(
                2,
                "Washed Blue Jeans",
                "36",
                false,
                "Geeta Mens",
                "https://ih1.redbubble.net/image.4373474571.0812/ssrco,classic_tee,womens,fafafa:ca443f4786,front_alt,square_product,600x600.jpg",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            )
        )
        listProduct.add(
            ProductModel(
                3,
                "Printed Shirt",
                "28",
                false,
                "Geeta Mens",
                "https://ih1.redbubble.net/image.5165706440.7185/ssrco,slim_fit_t_shirt,womens,101010:01c5ca27c6,front,square_product,600x600.jpg",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
            )
        )
    }

    private fun handleProduct() {
        productAdapter = ProductAdapter(this, object : IProduct {
            override fun callBackProduct(productModel: ProductModel) {
                var intent = Intent(this@MainActivity, ProductDetail::class.java)
                intent.putExtra(Common.KEY_PRODUCT_ORDER, productModel)
                resultLauncher.launch(intent)
            }

            override fun callbackFavorite(position: Int, isFavorites: Boolean) {
                productAdapter.updateDateByPosition(position, !isFavorites)
            }

        })
        binding.rcvProduct.adapter = productAdapter
        productAdapter.updateData(listProduct)
    }

    private fun handleCategories() {
        categoriesAdapter = CategoryAdapter(this, object : CategoryAdapter.ICallBack {
            override fun callBack(position: Int, categories: String) {
                categoriesAdapter.updatePosition(position)

            }

        })
        var layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.SPACE_BETWEEN
        binding.rcvCategories.layoutManager = layoutManager
        binding.rcvCategories.adapter = categoriesAdapter
        categoriesAdapter.updateData(listCategories)
    }

    override fun getClassVM(): Class<MainVM> {
        return MainVM::class.java
    }

    override fun initViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}