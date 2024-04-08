package com.example.mtl_clothes.api.req

import com.google.gson.annotations.SerializedName

data class LoginReq(
    var email: String,
    var password: String
)