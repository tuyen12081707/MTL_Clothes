package com.example.mtl_clothes.api.req

import com.google.gson.annotations.SerializedName

data class OrderReq(
    @SerializedName("userId") val userId: String,
    @SerializedName("products") val products: ArrayList<ProductOrder>,
    @SerializedName("shippingAddress") val shippingAddress: String,
    @SerializedName("telephone") val telephone: String,
    @SerializedName("shippingCity") val shippingCity: String,
    @SerializedName("discountCode") val discountCode: String
)

data class ProductOrder(
    @SerializedName("productId") val productId: String,
    @SerializedName("quantity") val quantity: Int,
)
