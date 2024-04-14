package com.example.mtl_clothes.api.res

import com.example.mtl_clothes.api.req.ProductOrder
import com.google.gson.annotations.SerializedName

data class OrderRes(
    @SerializedName("message") val message: String,
    @SerializedName("order") val order: Order
)

data class Order(
    @SerializedName("userId") val userId: String,
    @SerializedName("products") val products: ArrayList<ProductOrderRes>,
    @SerializedName("totalPriceProduct") val totalPriceProduct: Int,
    @SerializedName("totalPrice") val totalPrice: Int,
    @SerializedName("status") val status: String,
    @SerializedName("shippingAddress") val shippingAddress: String,
    @SerializedName("shippingCity") val shippingCity: String,
    @SerializedName("telephone") val telephone: String,
    @SerializedName("totalShip") val totalShip: Int,
    @SerializedName("_id") val id: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("__v") val version: Int
)

data class ProductOrderRes(
    @SerializedName("productId") val productId: String,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("_id") val id: String
)