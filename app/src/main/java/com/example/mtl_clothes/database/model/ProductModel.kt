package com.example.mtl_clothes.database.model

import com.example.mtl_clothes.api.res.ProductRes
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductModel(
    val name: String,

    val typeOf: String,

    val category: String,

    val description: String,

    val price: Int,

    val quantity: Int,

    val brand: String,

    val photos: List<String>,

    val size: List<String>,

    val id: String,

    val createdAt: String,

    val updatedAt: String,
    val version: Int
){
    constructor(productRes: ProductRes) : this(
        productRes.name,
        productRes.typeOf,
        productRes.category,
        productRes.description,
        productRes.price,
        productRes.quantity,
        productRes.brand,
        productRes.photos,
        productRes.size,
        productRes.id,
        productRes.createdAt,
        productRes.updatedAt,
        productRes.version
    )
}