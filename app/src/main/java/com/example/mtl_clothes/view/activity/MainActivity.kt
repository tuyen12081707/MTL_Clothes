package com.example.mtl_clothes.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mtl_clothes.R
import com.example.mtl_clothes.databinding.ActivityMainBinding
import com.example.mtl_clothes.view.adapter.CategoryAdapter
import com.example.mtl_clothes.viewmodel.CommonVM
import com.example.mtl_clothes.viewmodel.MainVM
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class MainActivity : BaseActivity<ActivityMainBinding, MainVM>() {
    private lateinit var adapter:CategoryAdapter
    var listCategories:MutableList<String> = mutableListOf()
    override fun initView() {
        listCategories.add("Popular")
        listCategories.add("Mens")
        listCategories.add("Women")
        listCategories.add("Sales")
        adapter = CategoryAdapter(this,object :CategoryAdapter.ICallBack{
            override fun callBack(position: Int, categories: String) {
                adapter.updatePosition(position)

            }

        })
        var layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.SPACE_BETWEEN
        binding.rcvCategories.layoutManager = layoutManager
        binding.rcvCategories.adapter = adapter
        adapter.updateData(listCategories)
    }

    override fun getClassVM(): Class<MainVM> {
        return MainVM::class.java
    }

    override fun initViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}