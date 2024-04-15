package com.example.mtl_clothes.view.activity

import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mtl_clothes.base_interface.IProduct
import com.example.mtl_clothes.databinding.ActivityMainBinding
import com.example.mtl_clothes.api.res.ProductRes
import com.example.mtl_clothes.ultis.Common
import com.example.mtl_clothes.ultis.setVisible
import com.example.mtl_clothes.view.adapter.CategoryAdapter
import com.example.mtl_clothes.view.adapter.ProductAdapter
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
    var listProduct: MutableList<ProductRes> = mutableListOf()
    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {

            }
        }

    override fun initView() {
        if (Common.countRate % 2 == 0) {
            Common.showRate(this)
        }
        //callapi product
        viewModel.getAllProduct(this)
        showDialog(this)
        handleCategories()
        handleProduct()
        binding.ivMenu.setOnClickListener {
            openDrawer(true)
        }
        var user = Common.getLoginRes(this)
        binding.tvName.text = user?.user?.username
        binding.tvEmail.text = user?.user?.email
        binding.drawer.setOnClickListener {

        }
        binding.ivClose.setOnClickListener {
            openDrawer(false)
        }
        binding.logout.setOnClickListener {
            Common.clearLoginRes(this)
            Common.setUserID(this, "")
            Common.setBearerToken(this, "")
            binding.tvName.text = "Admin"
            binding.tvEmail.text = "admin123@gmail.com"

        }
        binding.lnAddress.setOnClickListener {

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
        if (Common.listOrder.isNotEmpty()) {
            binding.ivCountOrder.setVisible(true)
            binding.ivCountOrder.text = Common.listOrder.size.toString()
            binding.btnOrder.setOnClickListener {
                resultLauncher.launch(Intent(this, OrderActivity::class.java))
            }
        } else {
            binding.ivCountOrder.setVisible(false)

        }
    }


    private fun handleProduct() {
        productAdapter = ProductAdapter(this, object : IProduct {
            override fun callBackProduct(productRes: ProductRes) {
                var intent = Intent(this@MainActivity, ProductDetail::class.java)
                intent.putExtra(Common.KEY_PRODUCT_ORDER, productRes.id)
                resultLauncher.launch(intent)
            }

        })
        binding.rcvProduct.adapter = productAdapter
        viewModel.listProduct.observe(this) {
            if (it.isNotEmpty()) {
                disableDialog(this)
                listProduct.clear()
                listProduct.addAll(it)
                productAdapter.updateData(listProduct)
            }
        }
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