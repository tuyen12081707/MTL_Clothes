package com.example.mtl_clothes.base_interface

import com.example.mtl_clothes.model.ProductModel

interface IProduct {
    fun callBackProduct(productModel: ProductModel)
    fun callbackFavorite(position:Int,isFavorites: Boolean)
}