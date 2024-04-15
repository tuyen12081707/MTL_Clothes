package com.example.mtl_clothes.ultis

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import com.codemybrainsout.ratingdialog.MaybeLaterCallback
import com.codemybrainsout.ratingdialog.RateButtonCallback
import com.codemybrainsout.ratingdialog.RatingDialog
import com.example.mtl_clothes.R
import com.example.mtl_clothes.api.res.LoginRes
import com.example.mtl_clothes.api.res.ProductRes
import com.google.gson.Gson
import java.io.Serializable

object Common {

    val KEY_PRODUCT_ORDER = "KEY_PRODUCT_ORDER"
    val KEY_COME_ORDER = "KEY_COME_ORDER"
    @JvmStatic
    var listOrder: MutableList<ProductRes> = mutableListOf()
    var countRate = 1

    private const val KEY_LOGIN_RES = "login_res"

    fun saveLoginRes(mContext: Context, loginRes: LoginRes) {
        try {
            val gson = Gson()
            val json = gson.toJson(loginRes)
            val preferences = mContext.getSharedPreferences(mContext.packageName, Context.MODE_PRIVATE)
            preferences.edit().putString(KEY_LOGIN_RES, json).apply()
        }catch (e:Exception){

        }

    }
    @JvmStatic
    fun showRate(context: Context) {
        val ratingDialog: RatingDialog = RatingDialog.Builder(context as Activity)
            .session(1)
            .date(1)
            .setNameApp(context.getString(R.string.app_name))
            .setIcon(R.drawable.img_logo)
            .setEmail("duonghuynh0240@gmail.com")
            .setOnlickRate(RateButtonCallback { rate ->
            })
            .ignoreRated(false)
            .isShowButtonLater(true)
            .isClickLaterDismiss(true)
            .setTextButtonLater("Maybe Later")
            .setOnlickMaybeLate(MaybeLaterCallback {

            })
            .ratingButtonColor(R.color.black)
            .build()
        ratingDialog.setCanceledOnTouchOutside(false)
        ratingDialog.show()
    }

    fun clearLoginRes(mContext: Context) {
        val preferences = mContext.getSharedPreferences(mContext.packageName, Context.MODE_PRIVATE)
        preferences.edit().remove(KEY_LOGIN_RES).apply()
    }
    fun getLoginRes(mContext: Context): LoginRes? {
        val preferences = mContext.getSharedPreferences(mContext.packageName, Context.MODE_PRIVATE)
        val json = preferences.getString(KEY_LOGIN_RES, null)
        val gson = Gson()
        return gson.fromJson(json, LoginRes::class.java)
    }
    fun <T : Serializable?> getSerializable(intent: Intent, key: String, m_class: Class<T>): T {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra(key, m_class)!!
        else
            intent.getSerializableExtra(key) as T
    }
    fun getUserId(mContext: Context): String? {
        val preferences =
            mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
        return preferences.getString("KEY_SUCCESS", "")
    }

    fun setUserID(context: Context, id: String) {
        val preferences =
            context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
        preferences.edit().putString("KEY_SUCCESS", id).apply()
    }
    fun getBearerToken(mContext: Context): String? {
        val preferences =
            mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
        return preferences.getString("KEY_BEARER", "")
    }

    fun setBearerToken(context: Context, id: String) {
        val preferences =
            context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
        preferences.edit().putString("KEY_BEARER", id).apply()
    }
    fun getShippingAddress(mContext: Context): String? {
        val preferences =
            mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
        return preferences.getString("KEY_SHIPPING_ADDRESS", "")
    }

    fun setShippingAddress(context: Context, content: String) {
        val preferences =
            context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
        preferences.edit().putString("KEY_SHIPPING_ADDRESS", content).apply()
    }
    fun getShippingCity(mContext: Context): String? {
        val preferences =
            mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
        return preferences.getString("KEY_SHIPPING_CITY", "")
    }

    fun setShippingCity(context: Context, content: String) {
        val preferences =
            context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
        preferences.edit().putString("KEY_SHIPPING_CITY", content).apply()
    }
    fun getTelephone(mContext: Context): String? {
        val preferences =
            mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
        return preferences.getString("KEY_TELEPHONE", "")
    }

    fun setTelphone(context: Context, content: String) {
        val preferences =
            context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
        preferences.edit().putString("KEY_TELEPHONE", content).apply()
    }
    fun getPromoCode(mContext: Context): String? {
        val preferences =
            mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
        return preferences.getString("KEY_PROMO_CODE", "")
    }

    fun setPromoCode(context: Context, content: String) {
        val preferences =
            context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
        preferences.edit().putString("KEY_PROMO_CODE", content).apply()
    }
}