package com.example.mtl_clothes.api.req

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ProductReq (
    @SerializedName("message")
    var message: String,
    @SerializedName("product")
    var product: Product
) : Serializable

data class Product(
    @SerializedName("name")
    val name: String,

    @SerializedName("typeOf")
    val typeOf: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("quantity")
    val quantity: Int,

    @SerializedName("brand")
    val brand: String,

    @SerializedName("photos")
    val photos: MutableList<String>,

    @SerializedName("size")
    val size: MutableList<String>,

    @SerializedName("_id")
    val id: String,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,

    @SerializedName("__v")
    val version: Int
)