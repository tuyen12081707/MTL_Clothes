package com.example.mtl_clothes.api.res

import com.google.gson.annotations.SerializedName

class BrandRes (
    @SerializedName("_id")
    val id: String,

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
    val photos: List<String>,

    @SerializedName("size")
    val size: List<String>,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,

    @SerializedName("__v")
    val version: Int
)