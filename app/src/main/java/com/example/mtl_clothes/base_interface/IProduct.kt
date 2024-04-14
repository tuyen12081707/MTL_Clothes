package com.example.mtl_clothes.base_interface

import com.example.mtl_clothes.api.res.ProductRes

interface IProduct {
    fun callBackProduct(ProductRes: ProductRes)
}