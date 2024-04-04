package com.example.mtl_clothes.base_interface

import com.example.mtl_clothes.model.ProductModel


interface IOrder {
    fun callBackOrder(productModel: ProductModel)
    fun callbackDelete(position:Int)
}