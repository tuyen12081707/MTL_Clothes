package com.example.mtl_clothes.model

import java.io.Serializable

class ProductModel(
    var id: Int,
    var name: String,
    var prices: String,
    var isFavorites: Boolean,
    var category: String,
    var imageLink:String,
    var description: String
):Serializable