package com.example.mtl_clothes.database.callback

import com.example.mtl_clothes.database.model.OrderModel

interface GetAllCallBack {
    fun onSucess(data:List<OrderModel>)
}