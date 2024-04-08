package com.example.mtl_clothes.api.res

import com.google.gson.annotations.SerializedName

class LoginRes(
    var token: String,
    var refreshToken: String,
    var user: User
)

data class User(
    @SerializedName("_id")
    val id: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("isAdmin")
    val isAdmin: Boolean,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,

    @SerializedName("__v")
    val version: Int
)