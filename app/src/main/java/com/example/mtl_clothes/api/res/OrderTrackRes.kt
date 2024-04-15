package com.example.mtl_clothes.api.res

import com.google.gson.annotations.SerializedName


class OrderTrackRes {
    @SerializedName("orders")
    var listOrders: ArrayList<OrderSub>? = null
}

class OrderSub {
    @SerializedName("_id")
    val id: String? = null

    @SerializedName("userId")
    val userId: String? = null

    @SerializedName("products")
    val products: ArrayList<ProductOrderRes>? = null

    @SerializedName("totalPriceProduct")
    val totalPriceProduct = 0

    @SerializedName("totalPrice")
    val totalPrice = 0

    @SerializedName("status")
    val status: String? = null

    @SerializedName("shippingAddress")
    val shippingAddress: String? = null

    @SerializedName("shippingCity")
    val shippingCity: String? = null

    @SerializedName("shippingWard")
    val shippingWard: String? = null

    @SerializedName("shippingDistrict")
    val shippingDistrict: String? = null

    @SerializedName("totalShip")
    val totalShip = 0

    @SerializedName("createdAt")
    val createdAt: String? = null

    @SerializedName("updatedAt")
    val updatedAt: String? = null

    @SerializedName("__v")
    val version: Int = 0
}


