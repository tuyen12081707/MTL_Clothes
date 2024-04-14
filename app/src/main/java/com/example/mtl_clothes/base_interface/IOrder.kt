package com.example.mtl_clothes.base_interface

import com.example.mtl_clothes.database.model.OrderModel


interface IOrder {
    fun callBackOrder(order: OrderModel)
    fun callbackDelete(order: OrderModel)
}