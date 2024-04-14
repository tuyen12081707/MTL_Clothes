package com.example.mtl_clothes.view.activity

import android.content.Intent
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.mtl_clothes.R
import com.example.mtl_clothes.api.req.OrderReq
import com.example.mtl_clothes.api.req.ProductOrder
import com.example.mtl_clothes.api.req.ProductReq
import com.example.mtl_clothes.base_interface.IOrder
import com.example.mtl_clothes.databinding.ActivityOrderBinding
import com.example.mtl_clothes.database.DatabaseRepository
import com.example.mtl_clothes.database.model.OrderModel
import com.example.mtl_clothes.database.callback.GetAllCallBack
import com.example.mtl_clothes.database.callback.InsertCallBack
import com.example.mtl_clothes.database.model.ProductModel
import com.example.mtl_clothes.ultis.Common
import com.example.mtl_clothes.ultis.ConvertCurrency
import com.example.mtl_clothes.view.adapter.OrderAdapter
import com.example.mtl_clothes.view.fragment.BottomSheetFragment
import com.example.mtl_clothes.viewmodel.CommonVM
import com.example.mtl_clothes.viewmodel.OrderVM

class OrderActivity : BaseActivity<ActivityOrderBinding, OrderVM>() {
    private var totalPrices: Int = 0
    private lateinit var orderAdapter: OrderAdapter
    var listOrder: ArrayList<OrderModel> = arrayListOf()
    var listProduct: ArrayList<ProductOrder> = arrayListOf()
    override fun initView() {
        showDialog(this)
        binding.tvTotalPrices.isSelected = true

        orderAdapter = OrderAdapter(this, object : IOrder {
            override fun callBackOrder(order: OrderModel) {

            }

            override fun callbackDelete(order: OrderModel) {
                DatabaseRepository.deleteOrder(this@OrderActivity, order, object : InsertCallBack {
                    override fun onSucess() {
                        DatabaseRepository.getAllOrder(this@OrderActivity, object : GetAllCallBack {
                            override fun onSucess(data: List<OrderModel>) {
                                orderAdapter.updateData(data)
                                listOrder.clear()
                                listOrder.addAll(data)
                                handleSetTotalPrices()
                            }

                        })
                    }
                })
            }

        })
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.rcvOrder.adapter = orderAdapter
        DatabaseRepository.getAllOrder(this, object : GetAllCallBack {
            override fun onSucess(data: List<OrderModel>) {
                disableDialog(this@OrderActivity)
                orderAdapter.updateData(data)
                listOrder.clear()
                listOrder.addAll(data)
                handleSetTotalPrices()
            }

        })

        binding.btnAdd.setOnClickListener {
            if (Common.getUserId(this) != "") {
                handleAdd()
            } else {
                startActivity(
                    Intent(
                        this,
                        LoginActivity::class.java
                    ).putExtra(Common.KEY_COME_ORDER, "true")
                )
            }
        }
        BottomSheetFragment.clickAdd.observe(this) {
            if (it) {
                listProduct.clear()
                for (order in listOrder) {
                    listProduct.add(ProductOrder(order.id, order.quantity_order))
                }
                val userId = Common.getUserId(this)
                val shippingAddress = Common.getShippingAddress(this)
                val telephone = Common.getTelephone(this)
                val shippingCity = Common.getShippingCity(this)
                val promoCode = Common.getPromoCode(this)
                if (shippingAddress != null && telephone != null && shippingCity != null && userId != null) {
                    if (!promoCode.isNullOrBlank()) {
                        var orderReq = OrderReq(
                            userId,
                            listProduct,
                            shippingAddress,
                            telephone,
                            shippingCity,
                            promoCode
                        )
                        viewModel.makeOrder(this@OrderActivity, orderReq)
                    } else {
                        var orderReq = OrderReq(
                            userId,
                            listProduct,
                            shippingAddress,
                            telephone,
                            shippingCity,
                            ""
                        )
                        viewModel.makeOrder(this@OrderActivity, orderReq)
                    }
                } else {
                    Toast.makeText(this@OrderActivity, "Invalid value", Toast.LENGTH_SHORT).show()
                }
                BottomSheetFragment.clickAdd.postValue(false)
            }
        }
        viewModel.liveOrder.observe(this){
            if(it!=null){
                resultLauncher.launch(Intent(this@OrderActivity,SucessActivity::class.java))
                Toast.makeText(this@OrderActivity,"Order Success",Toast.LENGTH_SHORT).show()

            }
        }
        BottomSheetFragment.clickAddress.observe(this) {
            if (it) {
                resultLauncher.launch(Intent(this, AddressActivity::class.java))
                BottomSheetFragment.clickAddress.postValue(false)
                BottomSheetFragment.clickAddress.removeObservers(this)

            }
        }
    }

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {

            }
        }

    private fun handleSetTotalPrices() {
        totalPrices = 0
        for (order in listOrder) {
            totalPrices += order.quantity_order * order.price

        }
        binding.tvTotalPrices.text =
            ConvertCurrency.getInstance().convertToUSD(totalPrices.toFloat())
    }

    private fun handleAdd() {
        val bottomSheetFragment = BottomSheetFragment.newInstance(this, totalPrices)
        bottomSheetFragment.setStyle(
            DialogFragment.STYLE_NORMAL,
            R.style.CustomBottomSheetDialogTheme
        )
        bottomSheetFragment.show(supportFragmentManager, BottomSheetFragment.TAG)
    }

    override fun getClassVM(): Class<OrderVM> {
        return OrderVM::class.java
    }

    override fun initViewBinding(): ActivityOrderBinding {
        return ActivityOrderBinding.inflate(layoutInflater)
    }

}