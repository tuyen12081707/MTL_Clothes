package com.example.mtl_clothes.api

import com.example.mtl_clothes.api.req.LoginReq
import com.example.mtl_clothes.api.req.OrderReq
import com.example.mtl_clothes.api.req.ProductReq
import com.example.mtl_clothes.api.req.RegisterReq
import com.example.mtl_clothes.api.res.LoginRes
import com.example.mtl_clothes.api.res.OrderRes
import com.example.mtl_clothes.api.res.OrderTrackRes
import com.example.mtl_clothes.api.res.ProductRes
import com.example.mtl_clothes.api.res.RegisterRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIServices {
    @Headers("Content-Type: application/json")
    @GET("products/all")
    fun getAllProduct(): Call<MutableList<ProductRes>>

    @Headers("Content-Type: application/json")
    @GET("products/details/{productId}")
    fun getProductDetail(@Path("productId") productId: String): Call<ProductRes>

    @Headers("Content-Type: application/json")
    @POST("auth/login")
    fun getApiLogin(
        @Body accountInfo: LoginReq
    ): Call<LoginRes>
    @Headers("Content-Type: application/json")
    @POST("auth/register")
    fun postRegister(
        @Body registerReq: RegisterReq
    ): Call<RegisterRes>
    @Headers("Content-Type: application/json")
    @POST("checkout")
    fun postOrder(
        @Body orderReq: OrderReq
    ): Call<OrderRes>

    @Headers("Content-Type: application/json")
    @GET("orders/{userId}")
    fun getAllOrder(
        @Path("userId") userId: String
    ): Call<OrderTrackRes>
}